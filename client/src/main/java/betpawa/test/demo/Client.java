package betpawa.test.demo;

import betpawa.test.demo.grpc.*;
import com.google.common.util.concurrent.FluentFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client
{
    public static void main(String[] args)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9999).usePlaintext().build();
        WalletServiceGrpc.WalletServiceFutureStub service = WalletServiceGrpc.newFutureStub(channel);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Long userId = 1L;

        FluentFuture<PaymentResponse> roundA = roundA(service, executor, userId);
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
