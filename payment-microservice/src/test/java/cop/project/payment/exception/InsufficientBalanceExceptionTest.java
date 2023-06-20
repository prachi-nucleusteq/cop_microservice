package cop.project.payment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InsufficientBalanceExceptionTest {

    @Test
    public void testBadRequestException() {
        String errorMessage = "Not found";
        InsufficientBalanceException notFoundException = new InsufficientBalanceException(errorMessage);
        assertEquals(errorMessage, notFoundException.getMessage());
    }
}
