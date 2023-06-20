package cop.project.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cop.project.product.dbo.Product;

public class ResponseDtoTest {

    @Test
    public void testGetterAndSetterAndToString() {
        ResponseDto responseDto = new ResponseDto();

        String message = "message";
        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        productList.add(product);

        Product product1 = new Product();
        product1.setProductName("name");
        product1.setProductDescription("description");
        product1.setPrice(23.5);
        product1.setNumberOfProduct(3);
        product1.setManufacturedBy("manufactured");
        product1.setId(3);
        product1.setDiscount("15%");
        product1.setCurrencySymbol("$");
        product1.setCurrency("USA");

        productList.add(product1);

        assertNull(responseDto.getMessage());
        assertNull(responseDto.getProductList());

        responseDto.setMessage(message);
        responseDto.setProductList(productList);

        assertEquals(message, responseDto.getMessage());
        assertEquals(productList, responseDto.getProductList());

        String expectedString = "ResponseDto(message=message, productList= " + productList + ")";
        assertEquals(expectedString, responseDto.toString());
    }
}

