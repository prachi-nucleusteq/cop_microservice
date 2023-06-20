package cop.project.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cop.project.user.dbo.Location;

public class UserInformationDtoTest {

    @Test
    public void testGetterAndSetter() {
        UserInformationDto userInformation = new UserInformationDto();

        userInformation.setUserName("John Doe");
        userInformation.setAddress("123 Main St");
        userInformation.setCountry("USA");
        userInformation.setState("California");
        userInformation.setPincode(12345);
        userInformation.setEmailId("john.doe@example.com");
        userInformation.setPassword("password");
        userInformation.setContactNumber(1234567890L);
        userInformation.setIsBusiness(true);
        userInformation.setGender("Male");
        userInformation.setAccountStatus("Active");
        userInformation.setBusinessName("ABC Company");
        userInformation.setBusinessContactNumber(9876543210L);

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
        userInformation.setLocation(locations);

        assertEquals("John Doe", userInformation.getUserName());
        assertEquals("123 Main St", userInformation.getAddress());
        assertEquals("USA", userInformation.getCountry());
        assertEquals("California", userInformation.getState());
        assertEquals(12345, userInformation.getPincode());
        assertEquals("john.doe@example.com", userInformation.getEmailId());
        assertEquals("password", userInformation.getPassword());
        assertEquals(1234567890L, userInformation.getContactNumber());
        assertTrue(userInformation.getIsBusiness());
        assertEquals("Male", userInformation.getGender());
        assertEquals("Active", userInformation.getAccountStatus());
        assertEquals("ABC Company", userInformation.getBusinessName());
        assertEquals(9876543210L, userInformation.getBusinessContactNumber());
        assertEquals(locations, userInformation.getLocation());
    }

    @Test
    public void testToString() {
        UserInformationDto userInformation = new UserInformationDto();
        userInformation.setUserName("John Doe");
        userInformation.setAddress("123 Main St");
        userInformation.setCountry("USA");
        userInformation.setState("California");
        userInformation.setPincode(12345);
        userInformation.setEmailId("john.doe@example.com");
        userInformation.setPassword("password");
        userInformation.setContactNumber(1234567890L);
        userInformation.setIsBusiness(true);
        userInformation.setGender("Male");
        userInformation.setAccountStatus("Active");
        userInformation.setBusinessName("ABC Company");
        userInformation.setBusinessContactNumber(9876543210L);
        userInformation.setLocation(null);

        String expectedString = "UserInformationDto(userName=John Doe, address=123 Main St, country=USA, state=California, pincode=12345,"
                + " emailId=john.doe@example.com, password=password, contactNumber=1234567890, isBusiness=true, gender=Male, accountStatus=Active, businessName=ABC Company, businessContactNumber=9876543210, location=null)";
        assertEquals(expectedString, userInformation.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserInformationDto userInformation1 = new UserInformationDto();
        userInformation1.setUserName("John Doe");
        userInformation1.setAddress("123 Main St");
        userInformation1.setCountry("USA");
        userInformation1.setState("California");
        userInformation1.setPincode(12345);
        userInformation1.setEmailId("john.doe@example.com");
        userInformation1.setPassword("password");
        userInformation1.setContactNumber(1234567890L);
        userInformation1.setIsBusiness(true);
        userInformation1.setGender("Male");
        userInformation1.setAccountStatus("Active");
        userInformation1.setBusinessName("ABC Company");
        userInformation1.setBusinessContactNumber(9876543210L);
        userInformation1.setLocation(null);

        UserInformationDto userInformation2 = new UserInformationDto();
        userInformation2.setUserName("John Doe");
        userInformation2.setAddress("123 Main St");
        userInformation2.setCountry("USA");
        userInformation2.setState("California");
        userInformation2.setPincode(12345);
        userInformation2.setEmailId("john.doe@example.com");
        userInformation2.setPassword("password");
        userInformation2.setContactNumber(1234567890L);
        userInformation2.setIsBusiness(true);
        userInformation2.setGender("Male");
        userInformation2.setAccountStatus("Active");
        userInformation2.setBusinessName("ABC Company");
        userInformation2.setBusinessContactNumber(9876543210L);
        userInformation2.setLocation(null);

        assertEquals(userInformation1, userInformation2);

        assertEquals(userInformation1.hashCode(), userInformation2.hashCode());
    }
}
