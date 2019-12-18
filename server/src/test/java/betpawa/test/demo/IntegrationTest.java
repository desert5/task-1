package betpawa.test.demo;

import betpawa.test.demo.grpc.*;
import betpawa.test.demo.model.User;
import betpawa.test.demo.model.Wallet;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;

import static betpawa.test.demo.grpc.Currency.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
public class IntegrationTest
{
    @Autowired
    private UserRepository repository;

    @BeforeEach
    @Transactional
    public void init()
    {
        User entity = new User();
        entity.setId(1L);
        entity.setName("Roger");
        HashSet<Wallet> wallets = new HashSet<>();
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setAmount(new BigDecimal("0"));
        wallet.setCurrency(betpawa.test.demo.model.Currency.EUR);
        wallets.add(wallet);

        Wallet wallet2 = new Wallet();
        wallet2.setAmount(new BigDecimal("0"));
        wallet2.setCurrency(betpawa.test.demo.model.Currency.USD);
        wallet2.setId(2L);
        wallets.add(wallet2);

        Wallet wallet3 = new Wallet();
        wallet3.setAmount(new BigDecimal("0"));
        wallet3.setCurrency(betpawa.test.demo.model.Currency.GBP);
        wallet3.setId(3L);
        wallets.add(wallet3);

        entity.setWallets(wallets);
        repository.save(entity);
    }

    @Test
    public void integration()
    {
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

        checkBalances(blockingStub.getBalance(BalanceRequest.newBuilder()
                .setUserId(1)
                .build()), new BigDecimal("100"));

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

        checkBalances(blockingStub.getBalance(BalanceRequest.newBuilder()
                .setUserId(1)
                .build()), new BigDecimal("200"));

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

        checkBalances(blockingStub.getBalance(BalanceRequest.newBuilder()
                .setUserId(1)
                .build()), new BigDecimal("300"));


        blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build());

        checkBalances(blockingStub.getBalance(BalanceRequest.newBuilder()
                .setUserId(1)
                .build()), new BigDecimal("100"));

        /////

        Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("200").toPlainString())
                .setCurrency(USD)
                .build()
        ));
    }

    private void checkBalances(BalanceResponse balance, BigDecimal usdBalance)
    {
        Assertions.assertTrue(balance.getBalanceList().stream().filter(x -> x.getCurrency() == USD)
                .findFirst()
                .map(x -> new BigDecimal(x.getAmount()).compareTo(usdBalance) == 0)
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
