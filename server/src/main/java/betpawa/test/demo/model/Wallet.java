package betpawa.test.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Wallet
{
    @Id
    private Long id;

    private BigDecimal amount;
    private Currency currency;
}
