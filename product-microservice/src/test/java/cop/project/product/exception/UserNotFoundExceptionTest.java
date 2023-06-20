package cop.project.product.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class UserNotFoundExceptionTest {

    @Test
    public void testBadRequestException() {
        String errorMessage = "Not found";
        UserNotFoundException notFoundException = new UserNotFoundException(errorMessage);
        assertEquals(errorMessage, notFoundException.getMessage());
    }
}
