package cop.project.cart.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "cart")
@ToString
@EqualsAndHashCode
public class CartItem {

    @Id
    private Integer id;

    private String productName;

    private Integer quantity;
}
