package betpawa.test.demo;

import betpawa.test.demo.grpc.Currency;
import betpawa.test.demo.grpc.PaymentRequest;
import betpawa.test.demo.grpc.PaymentResponse;
import betpawa.test.demo.grpc.WalletServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.math.BigDecimal;

public class Client
{
    public static void main(String[] args)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9999).usePlaintext().build();
        WalletServiceGrpc.WalletServiceBlockingStub blockingStub = WalletServiceGrpc.newBlockingStub(channel);
        WalletServiceGrpc.WalletServiceStub asyncStub = WalletServiceGrpc.newStub(channel);

        PaymentResponse response = blockingStub.deposit(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(BigDecimal.ONE.toPlainString())
                .setCurrency(Currency.EUR)
                .build()
        );
    }
}
