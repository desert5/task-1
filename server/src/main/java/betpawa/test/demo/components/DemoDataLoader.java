package betpawa.test.demo.components;

import betpawa.test.demo.UserRepository;
import betpawa.test.demo.model.Currency;
import betpawa.test.demo.model.User;
import betpawa.test.demo.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class DemoDataLoader
{
    private static final Logger logger = LoggerFactory.getLogger(DemoDataLoader.class);

    @Autowired
    private UserRepository repository;

    @Value("${prepare.users}")
    private Integer nUsers;

    @PostConstruct
    void loadData()
    {
        for (long i = 1; i <= nUsers; ++i)
        {
            final long current = i;
            repository.findById(i).orElseGet(() -> {
                User entity = new User();
                entity.setName("Test user " + current);
                HashSet<Wallet> wallets = new HashSet<>();
                Wallet wallet;
                wallet = new Wallet();
                wallet.setCurrency(Currency.USD);
                wallet.setAmount(BigDecimal.ZERO);
                wallets.add(wallet);
                wallet = new Wallet();
                wallet.setCurrency(Currency.EUR);
                wallet.setAmount(BigDecimal.ZERO);
                wallets.add(wallet);
                wallet = new Wallet();
                wallet.setCurrency(Currency.GBP);
                wallet.setAmount(BigDecimal.ZERO);
                wallets.add(wallet);
                entity.setWallets(wallets);
                User createdUser = repository.save(entity);
                logger.info("Created user with id " + createdUser.getId());
                return createdUser;
            });
        }
    }
}
