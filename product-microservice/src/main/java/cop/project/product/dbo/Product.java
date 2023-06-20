package cop.project.product.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "product_db")
public class Product {

    @Id
    private Integer id;

    private String productName;

    private String productDescription;

    private Integer numberOfProduct;

    private String discount;

    private String manufacturedBy;

    private Double price;

    private String currency;

    private String currencySymbol;

}
