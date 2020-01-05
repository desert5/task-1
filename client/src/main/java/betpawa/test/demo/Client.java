package betpawa.test.demo;

import betpawa.test.demo.grpc.*;
import com.google.common.util.concurrent.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.util.concurrent.MoreExecutors.newDirectExecutorService;
import static java.util.Arrays.asList;

@SuppressWarnings("DuplicateExpressions")
@SpringBootApplication
public class Client implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final NoopFallback fallback = new NoopFallback();
    private static final String ROUNDS_PARAM = "rounds";
    private static final String THREADS_PARAM = "threads";
    private static final String USERS_PARAM = "users";

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException
    {
        Integer users = args.containsOption(USERS_PARAM) ? new Integer(args.getOptionValues(USERS_PARAM).get(0)) : 10;
        Integer threadsPerUser = args.containsOption(THREADS_PARAM) ? new Integer(args.getOptionValues(THREADS_PARAM).get(0)) : 10;
        Integer roundsPerThread = args.containsOption(ROUNDS_PARAM) ? new Integer(args.getOptionValues(ROUNDS_PARAM).get(0)) : 10;

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        WalletServiceGrpc.WalletServiceFutureStub service = WalletServiceGrpc.newFutureStub(channel);
        Random random = new Random();

        logger.info("Starting execution emulating "
                + users + " users, "
                + threadsPerUser + " threads per user and "
                + roundsPerThread + " rounds per thread");

        ArrayList<ExecutorService> executors = new ArrayList<>();
        for (long u = 1; u <= users; ++u)
        {
            final Long userId = u;
            ExecutorService executor = Executors.newFixedThreadPool(threadsPerUser);
            executors.add(executor);

            List<Callable<FluentFuture<?>>> rounds = asList(
                    () -> roundA(service, newDirectExecutorService(), userId),
                    () -> roundB(service, newDirectExecutorService(), userId),
                    () -> roundC(service, newDirectExecutorService(), userId));

            for (int i = 0; i < threadsPerUser; ++i) {
                executor.submit(() -> {
                    List<? extends FluentFuture<?>> futures = IntStream.range(0, roundsPerThread)
                            .mapToObj((x) -> rounds.get(random.nextInt(rounds.size())))
                            .map((fn) -> {
                                try
                                {
                                    return fn.call();
                                }
                                catch (Exception e)
                                {
                                    logger.error("Exception during round invocation", e);
                                    return null;
                                }
                            })
                            .collect(Collectors.toList());
                    logger.info("Thread is finished");
                });
            }
        }

        logger.info("Created " + executors.size() + " executor services. Waiting for them to finish");

        for (ExecutorService executor : executors)
        {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            logger.info("Executor service " + executor.toString() + " finished");
        }

        logger.info("All rounds completed, all executor services are shut down");
        System.exit(0);
    }

    private static FluentFuture<PaymentResponse> roundA(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
        logger.info("Creating round A sequence");

        return FluentFuture.from(service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build())
                ).transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("200")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.EUR)
                        .build()), e)
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e);
    }

    private static FluentFuture<PaymentResponse> roundB(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
        logger.info("Creating round B sequence");
        return FluentFuture.from(service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build())
                ).catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("300")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e);
    }

    private static FluentFuture<BalanceResponse> roundC(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
        logger.info("Creating round C sequence");
        return FluentFuture.from(service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build())
                ).transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("200")
                        .setCurrency(Currency.USD)
                        .build()), e)
                .catchingAsync(StatusRuntimeException.class, fallback, e)
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e);
    }

    private static class NoopFallback implements AsyncFunction<StatusRuntimeException, PaymentResponse> {
        @Override
        public ListenableFuture<PaymentResponse> apply(@NullableDecl StatusRuntimeException input) {
            logger.error("Can not withdraw funds: " + input.getMessage());
            return null;
        }
    }
}
