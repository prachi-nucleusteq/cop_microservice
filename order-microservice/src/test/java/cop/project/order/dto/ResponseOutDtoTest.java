package cop.project.order.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ResponseOutDtoTest {

    @Test
    public void testGetterAndSetter() {

        ResponseOutDto responseOutDto = new ResponseOutDto();
        assertNull(responseOutDto.getMessage());

        responseOutDto.setMessage("message");
        assertEquals("message", responseOutDto.getMessage());

        String expectedString = "ResponseOutDto(message=message)";
        assertEquals(expectedString, responseOutDto.toString());
    }
}
