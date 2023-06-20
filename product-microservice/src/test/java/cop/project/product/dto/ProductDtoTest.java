package cop.project.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ProductDtoTest {

    @Test
    public void testGetterAndSetterAndToString() {

        ProductDto productDto = new ProductDto();

        String productName = "product name";

        String productDescription = "product description";

        Integer numberOfProduct = 4;

        String discount = "12%";

        String manufacturedBy = "manufactured";

        Double price = 2.3;

        String currency = "USA";

        String currencySymbol = "$";

        assertNull(productDto.getProductName());
        assertNull(productDto.getProductDescription());
        assertNull(productDto.getCurrency());
        assertNull(productDto.getCurrencySymbol());
        assertNull(productDto.getDiscount());
        assertNull(productDto.getManufacturedBy());
        assertNull(productDto.getNumberOfProduct());
        assertNull(productDto.getPrice());

        productDto.setProductName(productName);
        productDto.setProductDescription(productDescription);
        productDto.setCurrency(currency);
        productDto.setCurrencySymbol(currencySymbol);
        productDto.setPrice(price);
        productDto.setManufacturedBy(manufacturedBy);
        productDto.setDiscount(discount);
        productDto.setNumberOfProduct(numberOfProduct);

        assertEquals(productName, productDto.getProductName());
        assertEquals(productDescription, productDto.getProductDescription());
        assertEquals(numberOfProduct, productDto.getNumberOfProduct());
        assertEquals(discount, productDto.getDiscount());
        assertEquals(manufacturedBy, productDto.getManufacturedBy());
        assertEquals(price, productDto.getPrice());
        assertEquals(currency, productDto.getCurrency());
        assertEquals(currencySymbol, productDto.getCurrencySymbol());

        String expectedString = "ProductDto(id=2, productName= " + productName + ", productDescription "
                + productDescription + ", numberOfProduct " + numberOfProduct + "," + "discount = " + discount
                + ", manufacturedBy= " + manufacturedBy + " ,price= " + price + ", currency= " + currency
                + ", currencySymbol= " + currencySymbol + " )";
        assertEquals(expectedString, productDto.toString());

    }
}
