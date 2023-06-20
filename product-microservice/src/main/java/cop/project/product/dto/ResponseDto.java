package cop.project.product.dto;

import java.util.List;

import cop.project.product.dbo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto {

    private String message;

    private List<Product> productList;

}
