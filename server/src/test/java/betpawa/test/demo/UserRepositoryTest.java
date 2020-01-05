package betpawa.test.demo;

import betpawa.test.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("local")
public class UserRepositoryTest
{
    @Autowired
    private UserRepository repository;

    @Test
    @Transactional
    public void test()
    {
        Optional<User> savedUser = repository.findById(1L);

        Assertions.assertTrue(savedUser.isPresent());
        Assertions.assertEquals("Test user 1", savedUser.get().getName());
        Assertions.assertEquals(3, savedUser.get().getWallets().size());
    }
}
