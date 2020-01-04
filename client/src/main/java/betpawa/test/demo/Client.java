package betpawa.test.demo;

import betpawa.test.demo.grpc.*;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Client implements ApplicationRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Integer roundsPerThread = 10;
        Integer threadsPerUser = 10;

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9999).usePlaintext().build();
        WalletServiceGrpc.WalletServiceFutureStub service = WalletServiceGrpc.newFutureStub(channel);
        ExecutorService executor = Executors.newFixedThreadPool(threadsPerUser);
        Random random = new Random();

        Long userId = 1L;

        List<Callable<FluentFuture<?>>> rounds = asList(
                () -> roundA(service, executor, userId),
                () -> roundB(service, executor, userId),
                () -> roundC(service, executor, userId));

        ArrayList<ListenableFuture<?>> join = new ArrayList<>();

        executor.submit(() -> {
            List<? extends FluentFuture<?>> futures = IntStream.range(0, roundsPerThread)
                    .mapToObj((i) -> rounds.get(random.nextInt(rounds.size())))
                    .map((fn) -> fn.call())
                    .collect(Collectors.toList());

            join.add(Futures.allAsList(futures));
        });

        Futures.whenAllComplete(join).call(() -> SpringApplication.exit(context, () -> 0), executor);
    }

    private static FluentFuture<PaymentResponse> roundA(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
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
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.USD)
                        .build()), e);
    }

    private static FluentFuture<PaymentResponse> roundB(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
        return FluentFuture.from(service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build())
                ).transformAsync((x) -> service.deposit(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("300")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e)
                .transformAsync((x) -> service.withdraw(PaymentRequest.newBuilder()
                        .setUserId(userId)
                        .setAmount("100")
                        .setCurrency(Currency.GBP)
                        .build()), e);
    }

    private static FluentFuture<BalanceResponse> roundC(WalletServiceGrpc.WalletServiceFutureStub service, ExecutorService e, Long userId) {
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
                .transformAsync((x) -> service.getBalance(BalanceRequest.newBuilder()
                        .setUserId(userId)
                        .build()), e);
    }
}
