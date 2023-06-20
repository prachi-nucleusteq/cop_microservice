package cop.project.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String productName;

    private String productDescription;

    private Integer numberOfProduct;

    private String discount;

    private String manufacturedBy;

    private String currencySymbol;

    private Double price;

    private String currency;
}
