package betpawa.test.demo;

import betpawa.test.demo.model.Currency;
import betpawa.test.demo.model.User;
import betpawa.test.demo.model.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest
{
    @Autowired
    private UserRepository repository;

    @Test
    public void test()
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
        wallet2.setAmount(new BigDecimal("200"));
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


        Optional<User> savedUser = repository.findById(1L);

        Assertions.assertTrue(savedUser.isPresent());
        Assertions.assertEquals("Roger", savedUser.get().getName());
        Assertions.assertEquals(3, savedUser.get().getWallets().size());
    }
}
