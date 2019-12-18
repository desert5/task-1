package betpawa.test.demo.service;

import betpawa.test.demo.UserRepository;
import betpawa.test.demo.grpc.*;
import betpawa.test.demo.model.Currency;
import betpawa.test.demo.model.User;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@GRpcService
public class WalletService extends WalletServiceGrpc.WalletServiceImplBase
{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void deposit(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver)
    {
        log.info("Got request to deposit " + request.getAmount() + " " + request.getCurrency().name() + " from user " + request.getUserId());

        Currency requestCurrency;
        BigDecimal requestAmount;
        try
        {
            requestCurrency = Currency.valueOf(request.getCurrency().name());
            requestAmount = new BigDecimal(request.getAmount());
        }
        catch (Exception e)
        {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("Unknown currency").withCause(e)));
            return;
        }

        Optional<User> user = userRepository.findById(request.getUserId());
        user.map(User::getWallets).flatMap(x -> x.stream()
                .filter(it -> it.getCurrency() == requestCurrency)
                .findAny())
                .ifPresent(x -> {
                    x.setAmount(x.getAmount().add(requestAmount));
                    userRepository.save(user.get());
                });

        responseObserver.onNext(PaymentResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void withdraw(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver)
    {
        log.info("Got request to withdraw " + request.getAmount() + " " + request.getCurrency().name() + " from user " + request.getUserId());

        Currency requestCurrency;
        BigDecimal requestAmount;
        try
        {
            requestCurrency = Currency.valueOf(request.getCurrency().name());
            requestAmount = new BigDecimal(request.getAmount());
        }
        catch (Exception e)
        {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("Unknown currency").withCause(e)));
            return;
        }

        Optional<User> user = userRepository.findById(request.getUserId());
        user
                .map(User::getWallets)
                .flatMap(wallets -> wallets.stream()
                        .filter(wallet -> wallet.getCurrency() == requestCurrency)
                        .findAny())
                .filter(wallet -> wallet.getAmount().subtract(requestAmount).compareTo(BigDecimal.ZERO) > 0)
                .map(x -> {
                    x.setAmount(x.getAmount().subtract(requestAmount));
                    userRepository.save(user.get());
                    responseObserver.onNext(PaymentResponse.newBuilder().build());
                    responseObserver.onCompleted();
                    return x;
                })
                .orElseGet(() -> {
                    responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("insufficient_funds")));
                    return null;
                });
    }

    @Override
    @Transactional
    public void getBalance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver)
    {
        log.info("Got request to get balance for user " + request.getUserId());

        userRepository.findById(request.getUserId())
                .map(x -> x.getWallets().stream())
                .map(wallets -> {
                            StringBuilder logString = new StringBuilder("Balances are: ");

                            responseObserver.onNext(BalanceResponse.newBuilder().addAllBalance(wallets.map(balance -> {
                                logString.append(" ").append(balance.getCurrency().name()).append(" ").append(balance.getAmount().toPlainString());

                                return Balance.newBuilder()
                                        .setAmount(balance.getAmount().toPlainString())
                                        .setCurrency(betpawa.test.demo.grpc.Currency.valueOf(balance.getCurrency().name()))
                                        .build();
                            })
                                    .collect(Collectors.toList()))
                                    .build());

                            responseObserver.onCompleted();

                            log.info(logString.toString());
                            return wallets;
                        }
                ).orElseGet(() -> {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("user_not_found")));
            return null;
        });
    }
}
