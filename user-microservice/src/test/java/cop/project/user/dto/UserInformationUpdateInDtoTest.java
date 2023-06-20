package cop.project.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cop.project.user.dbo.Location;

public class UserInformationUpdateInDtoTest {

    @Test
    public void testGetterAndSetter() {
        UserInformationUpdateInDto userInformationUpdate = new UserInformationUpdateInDto();

        userInformationUpdate.setUserName("John Doe");
        userInformationUpdate.setLocationId(1L);
        userInformationUpdate.setAddress("123 Main St");
        userInformationUpdate.setCountry("USA");
        userInformationUpdate.setState("California");
        userInformationUpdate.setPincode(12345);
        userInformationUpdate.setEmailId("john.doe@example.com");
        userInformationUpdate.setContactNumber(1234567890L);
        userInformationUpdate.setGender("Male");
        userInformationUpdate.setBusinessName("ABC Company");
        userInformationUpdate.setBusinessContactNumber(9876543210L);

        List<Location> locations = new ArrayList<>();
        Location location1 = new Location();
        location1.setBusinessId(1l);
        location1.setCountry("USA");
        location1.setId(2L);
        location1.setLocation("location");
        location1.setPincode(1312321);
        location1.setState("California");

        Location location2 = new Location();
        location2.setBusinessId(4l);
        location2.setCountry("USA");
        location2.setId(5L);
        location2.setLocation("location");
        location2.setPincode(1312321);
        location2.setState("California");

        locations.add(location1);
        locations.add(location2);
        userInformationUpdate.setLocation(locations);

        assertEquals("John Doe", userInformationUpdate.getUserName());
        assertEquals(1L, userInformationUpdate.getLocationId());
        assertEquals("123 Main St", userInformationUpdate.getAddress());
        assertEquals("USA", userInformationUpdate.getCountry());
        assertEquals("California", userInformationUpdate.getState());
        assertEquals(12345, userInformationUpdate.getPincode());
        assertEquals("john.doe@example.com", userInformationUpdate.getEmailId());
        assertEquals(1234567890L, userInformationUpdate.getContactNumber());
        assertEquals("Male", userInformationUpdate.getGender());
        assertEquals("ABC Company", userInformationUpdate.getBusinessName());
        assertEquals(9876543210L, userInformationUpdate.getBusinessContactNumber());
        assertEquals(locations, userInformationUpdate.getLocation());
    }

    @Test
    public void testToString() {
        UserInformationUpdateInDto userInformationUpdate = new UserInformationUpdateInDto();
        userInformationUpdate.setUserName("John Doe");
        userInformationUpdate.setLocationId(1L);
        userInformationUpdate.setAddress("123 Main St");
        userInformationUpdate.setEmailId("john.doe@example.com");
        userInformationUpdate.setGender("male");
        userInformationUpdate.setLocationId(2L);
        userInformationUpdate.setUserName("user name");
        userInformationUpdate.setState("state");
        userInformationUpdate.setPincode(7986680);
        userInformationUpdate.setCountry("country");
        userInformationUpdate.setContactNumber(5648654678L);
        userInformationUpdate.setBusinessContactNumber(9876545676L);
        userInformationUpdate.setBusinessName("businessName");

        String expectedString = "UserInformationUpdateInDto(userName=user name, locationId=2, address=123 Main St, country=country, state=state, pincode=7986680, emailId=john.doe@example.com, contactNumber=5648654678, gender=male, businessName=businessName, businessContactNumber=9876545676, location=null)";
        assertEquals(expectedString, userInformationUpdate.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserInformationUpdateInDto userInformationUpdate1 = new UserInformationUpdateInDto();
        userInformationUpdate1.setUserName("John Doe");
        userInformationUpdate1.setLocationId(1L);
        userInformationUpdate1.setAddress("123 Main St");
        userInformationUpdate1.setEmailId("john.doe@example.com");

        UserInformationUpdateInDto userInformationUpdate2 = new UserInformationUpdateInDto();
        userInformationUpdate2.setUserName("John Doe");
        userInformationUpdate2.setLocationId(1L);
        userInformationUpdate2.setAddress("123 Main St");
        userInformationUpdate2.setEmailId("john.doe@example.com");

        assertEquals(userInformationUpdate1, userInformationUpdate2);

        assertEquals(userInformationUpdate1.hashCode(), userInformationUpdate2.hashCode());
    }
}
