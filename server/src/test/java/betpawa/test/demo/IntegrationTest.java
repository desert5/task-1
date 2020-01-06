package betpawa.test.demo;

import betpawa.test.demo.grpc.BalanceRequest;
import betpawa.test.demo.grpc.BalanceResponse;
import betpawa.test.demo.grpc.PaymentRequest;
import betpawa.test.demo.grpc.WalletServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static betpawa.test.demo.grpc.Currency.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
public class IntegrationTest
{
    @Autowired
    private UserRepository repository;

    @Test
    public void integration() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        WalletServiceGrpc.WalletServiceBlockingStub blockingStub = WalletServiceGrpc.newBlockingStub(channel);

        Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build()
        ));


        blockingStub.deposit(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("100").toPlainString())
                .setCurrency(USD)
                .build()
        );

        checkBalances(blockingStub, "100");

        //////

        Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build()
        ));


        blockingStub.deposit(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("100").toPlainString())
                .setCurrency(USD)
                .build()
        );

        checkBalances(blockingStub, "200");

///////////////

        Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build()
        ));


        blockingStub.deposit(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("100").toPlainString())
                .setCurrency(USD)
                .build()
        );

        checkBalances(blockingStub, "300");

        blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build());

        checkBalances(blockingStub, "100");

        /////

        Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build()
        ));
    }

    private void checkBalances(WalletServiceGrpc.WalletServiceBlockingStub blockingStub, String usdBalance) throws InterruptedException {

        // Wait for transaction to commit on server side
        Thread.sleep(50L);

        BalanceResponse balance = blockingStub.getBalance(BalanceRequest.newBuilder()
                .setUserId(1)
                .build());

        Assertions.assertTrue(balance.getBalanceList().stream().filter(x -> x.getCurrency() == USD)
                .findFirst()
                .map(x -> new BigDecimal(x.getAmount()).compareTo(new BigDecimal(usdBalance)) == 0)
                .orElse(Boolean.FALSE));

        Assertions.assertTrue(balance.getBalanceList().stream().filter(x -> x.getCurrency() == EUR)
                .findFirst()
                .map(x -> new BigDecimal(x.getAmount()).compareTo(BigDecimal.ZERO) == 0)
                .orElse(Boolean.FALSE));

        Assertions.assertTrue(balance.getBalanceList().stream().filter(x -> x.getCurrency() == GBP)
                .findFirst()
                .map(x -> new BigDecimal(x.getAmount()).compareTo(BigDecimal.ZERO) == 0)
                .orElse(Boolean.FALSE));
    }
}
