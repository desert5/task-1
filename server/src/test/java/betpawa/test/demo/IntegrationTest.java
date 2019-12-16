package betpawa.test.demo;

import betpawa.test.demo.grpc.PaymentRequest;
import betpawa.test.demo.grpc.PaymentResponse;
import betpawa.test.demo.grpc.WalletServiceGrpc;
import betpawa.test.demo.model.Currency;
import betpawa.test.demo.model.User;
import betpawa.test.demo.model.Wallet;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;

@Slf4j
@SpringBootTest
@Disabled
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
        wallet.setAmount(new BigDecimal("100"));
        wallet.setCurrency(Currency.EUR);
        wallets.add(wallet);

        Wallet wallet2 = new Wallet();
        wallet2.setAmount(new BigDecimal("100"));
        wallet2.setCurrency(Currency.USD);
        wallet2.setId(2L);
        wallets.add(wallet2);

        Wallet wallet3 = new Wallet();
        wallet3.setAmount(new BigDecimal("300"));
        wallet3.setCurrency(Currency.GBP);
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

        PaymentResponse response = blockingStub.withdraw(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(new BigDecimal("100").toPlainString())
                .setCurrency(PaymentRequest.Currency.EUR)
                .build()
        );

        PaymentResponse response2 = blockingStub.deposit(PaymentRequest.newBuilder()
                .setUserId(1)
                .setAmount(BigDecimal.ONE.toPlainString())
                .setCurrency(PaymentRequest.Currency.EUR)
                .build()
        );

        Assertions.assertNotNull(response);
    }
}
