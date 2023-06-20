package cop.project.payment.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "wallets")
public class Wallet {
    @Id
    private Integer id;
    private Integer userId;
    private String cardNumber;
    private double balance;
    private boolean isDefault;
}