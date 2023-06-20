package cop.project.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserDetailsOutDtoTest {

    @Test
    public void testGetterAndSetter() {
        UserDetailsOutDto userDetails = new UserDetailsOutDto();

        userDetails.setUserName("John Doe");
        userDetails.setAddress("123 Main St");
        userDetails.setEmailId("john@example.com");
        userDetails.setContactNumber(1234567890L);
        userDetails.setBusiness(false);
        userDetails.setBusinessName(null);
        userDetails.setLocation(new ArrayList<>());
        userDetails.setBusinesscontactNumber(null);
        userDetails.setBusinessAddress(null);
        userDetails.setCountry("USA");
        userDetails.setState("New York");
        userDetails.setPincode(12345);

        assertEquals("John Doe", userDetails.getUserName());
        assertEquals("123 Main St", userDetails.getAddress());
        assertEquals("john@example.com", userDetails.getEmailId());
        assertEquals(1234567890L, userDetails.getContactNumber());
        assertFalse(userDetails.isBusiness());
        assertNull(userDetails.getBusinessName());
        assertNotNull(userDetails.getLocation());
        assertEquals(0, userDetails.getLocation().size());
        assertNull(userDetails.getBusinesscontactNumber());
        assertNull(userDetails.getBusinessAddress());
        assertEquals("USA", userDetails.getCountry());
        assertEquals("New York", userDetails.getState());
        assertEquals(12345, userDetails.getPincode());
    }

    @Test
    public void testToString() {
        UserDetailsOutDto userDetails = new UserDetailsOutDto();
        userDetails.setUserName("John Doe");
        userDetails.setAddress("123 Main St");
        userDetails.setEmailId("john@example.com");
        userDetails.setContactNumber(1234567890L);
        userDetails.setBusiness(false);
        userDetails.setBusinessName(null);
        userDetails.setLocation(new ArrayList<>());
        userDetails.setBusinesscontactNumber(null);
        userDetails.setBusinessAddress(null);
        userDetails.setCountry("USA");
        userDetails.setState("New York");
        userDetails.setPincode(12345);

        String expectedString = "UserDetailsOutDto(userName=John Doe, address=123 Main St, emailId=john@example.com, "
                + "contactNumber=1234567890, isBusiness=false, businessName=null, location=[], "
                + "businesscontactNumber=null, businessAddress=null, country=USA, state=New York, pincode=12345)";
        assertEquals(expectedString, userDetails.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserDetailsOutDto userDetails1 = new UserDetailsOutDto();
        userDetails1.setUserName("John Doe");
        userDetails1.setAddress("123 Main St");
        userDetails1.setEmailId("john.doe@example.com");
        userDetails1.setContactNumber(1234567890L);

        UserDetailsOutDto userDetails2 = new UserDetailsOutDto();
        userDetails2.setUserName("John Doe");
        userDetails2.setAddress("123 Main St");
        userDetails2.setEmailId("john.doe@example.com");
        userDetails2.setContactNumber(1234567890L);

        assertEquals(userDetails1, userDetails2);

        assertEquals(userDetails1.hashCode(), userDetails2.hashCode());
    }
}
