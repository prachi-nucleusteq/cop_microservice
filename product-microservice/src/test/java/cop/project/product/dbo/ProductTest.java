package cop.project.product.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testGetterAndSetterAndToString() {

        Product product = new Product();
        Integer id = 2;

        String productName = "product name";

        String productDescription = "product description";

        Integer numberOfProduct = 4;

        String discount = "12%";

        String manufacturedBy = "manufactured";

        Double price = 2.3;

        String currency = "USA";

        String currencySymbol = "$";

        assertNull(product.getId());
        assertNull(product.getProductName());
        assertNull(product.getProductDescription());
        assertNull(product.getCurrency());
        assertNull(product.getCurrencySymbol());
        assertNull(product.getDiscount());
        assertNull(product.getManufacturedBy());
        assertNull(product.getNumberOfProduct());
        assertNull(product.getPrice());

        product.setId(id);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setCurrency(currency);
        product.setCurrencySymbol(currencySymbol);
        product.setPrice(price);
        product.setManufacturedBy(manufacturedBy);
        product.setDiscount(discount);
        product.setNumberOfProduct(numberOfProduct);

        assertEquals(id, product.getId());
        assertEquals(productName, product.getProductName());
        assertEquals(productDescription, product.getProductDescription());
        assertEquals(numberOfProduct, product.getNumberOfProduct());
        assertEquals(discount, product.getDiscount());
        assertEquals(manufacturedBy, product.getManufacturedBy());
        assertEquals(price, product.getPrice());
        assertEquals(currency, product.getCurrency());
        assertEquals(currencySymbol, product.getCurrencySymbol());

        String expectedString = "Product(id=2, productName= " + productName + ", productDescription "
                + productDescription + ", numberOfProduct " + numberOfProduct + "," + "discount = " + discount
                + ", manufacturedBy= " + manufacturedBy + " ,price= " + price + ", currency= " + currency
                + ", currencySymbol= " + currencySymbol + " )";
        assertEquals(expectedString, product.toString());

    }
}
