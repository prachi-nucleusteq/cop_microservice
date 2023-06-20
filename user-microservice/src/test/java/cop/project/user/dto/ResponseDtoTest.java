package cop.project.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ResponseDtoTest {

    @Test
    public void testGetterAndSetter() {
        ResponseDto responseDto = new ResponseDto();

        responseDto.setMessage("Success");

        assertEquals("Success", responseDto.getMessage());
    }

    @Test
    public void testEqualsAndHashCode() {
        ResponseDto responseDto1 = new ResponseDto();
        responseDto1.setMessage("Success");

        ResponseDto responseDto2 = new ResponseDto();
        responseDto2.setMessage("Success");

        assertEquals(responseDto1, responseDto2);

        assertEquals(responseDto1.hashCode(), responseDto2.hashCode());
    }
    
    @Test
    public void testToString() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");

        String expectedString = "ResponseDto(message=Success)";
        assertEquals(expectedString, responseDto.toString());
    }
}
