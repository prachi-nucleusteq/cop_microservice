package cop.project.order.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testGetterAndSetter() {

        Order order = new Order();
        assertNull(order.getId());
        assertNull(order.getBuyerId());
        assertNull(order.getProducts());

        order.setId(1);
        order.setBuyerId(10);

        List<String> list = new ArrayList<>();
        list.add("product1");
        list.add("product2");
        list.add("product3");

        order.setProducts(list);

        assertEquals(1, order.getId());
        assertEquals(10, order.getBuyerId());
        assertEquals(list, order.getProducts());

        String expectedString = "Order(id=1, buyerId=10, products=" + list + ")";
        assertEquals(expectedString, order.toString());}
}
