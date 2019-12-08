package betpawa.test.demo.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class User
{
    @Id
    private Long id;

    private String name;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private Set<Wallet> wallets;
}
