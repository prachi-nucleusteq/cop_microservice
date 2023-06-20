package cop.project.payment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PaymentRequestDtoTest {

    @Test
    public void gettersAndSetters() {
        double amount = 100.0;

        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(amount);

        assertEquals(amount, dto.getAmount());
    }

    @Test
    public void toStringtest() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(100.0);
        String expectedToString = "PaymentRequestDTO(amount=100.0)";

        String actualToString = dto.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void equalsAndHashMethodTest() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(100.0);

        assertEquals(dto, dto);

        PaymentRequestDTO dto1 = new PaymentRequestDTO();
        dto1.setAmount(100.0);

        PaymentRequestDTO dto2 = new PaymentRequestDTO();
        dto2.setAmount(100.0);

        assertEquals(dto1, dto2);

        PaymentRequestDTO dto3 = new PaymentRequestDTO();
        dto3.setAmount(100.0);

        assertNotEquals(dto1, null);

        PaymentRequestDTO dto4 = new PaymentRequestDTO();
        dto.setAmount(100.0);

        Object obj = new Object();

        assertNotEquals(dto4, obj);

        PaymentRequestDTO dto5 = new PaymentRequestDTO();
        dto1.setAmount(100.0);

        PaymentRequestDTO dto6 = new PaymentRequestDTO();
        dto2.setAmount(200.0);


        PaymentRequestDTO dto7 = new PaymentRequestDTO();
        dto1.setAmount(100.0);

        PaymentRequestDTO dto8 = new PaymentRequestDTO();
        dto2.setAmount(100.0);

        assertEquals(dto7.hashCode(), dto8.hashCode());
    }
}
