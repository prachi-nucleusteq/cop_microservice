package cop.project.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginDtoTest {

    @Test
    public void testGetterAndSetter() {
        LoginDto loginDto = new LoginDto();

        loginDto.setEmailId("john@example.com");
        loginDto.setPassword("password123");

        assertEquals("john@example.com", loginDto.getEmailId());
        assertEquals("password123", loginDto.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setEmailId("john@example.com");
        loginDto1.setPassword("password123");

        LoginDto loginDto2 = new LoginDto();
        loginDto2.setEmailId("john@example.com");
        loginDto2.setPassword("password123");

        assertEquals(loginDto1, loginDto2);
        assertEquals(loginDto1.hashCode(), loginDto2.hashCode());
    }

    @Test
    public void testToString() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmailId("john@example.com");
        loginDto.setPassword("password123");

        String expectedString = "LoginDto(emailId=john@example.com, password=password123)";
        assertEquals(expectedString, loginDto.toString());
    }
}
