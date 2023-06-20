package cop.project.user.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetterAndSetter() {
        User user = new User();

        assertNull(user.getId());
        assertNull(user.getAccountStatus());
        assertNull(user.getAddress());
        assertNull(user.getBusinessId());
        assertNull(user.getContactNumber());
        assertNull(user.getCountry());
        assertNull(user.getEmailId());
        assertNull(user.getGender());
        assertNull(user.getIsBusiness());
        assertNull(user.getPassword());
        assertNull(user.getPincode());
        assertNull(user.getState());
        assertNull(user.getUserName());

        user.setId(1L);
        user.setUserName("John");
        user.setBusinessId(123L);
        user.setAddress("123 Main St");
        user.setCountry("USA");
        user.setState("NY");
        user.setPincode(12345);
        user.setEmailId("john@example.com");
        user.setContactNumber(1234567890L);
        user.setIsBusiness(true);
        user.setGender("Male");
        user.setAccountStatus("Active");
        user.setPassword("password");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getUserName());
        assertEquals(123L, user.getBusinessId());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("USA", user.getCountry());
        assertEquals("NY", user.getState());
        assertEquals(12345, user.getPincode());
        assertEquals("john@example.com", user.getEmailId());
        assertEquals(1234567890L, user.getContactNumber());
        assertTrue(user.getIsBusiness());
        assertEquals("Male", user.getGender());
        assertEquals("Active", user.getAccountStatus());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {

        Long id = 2l;
        String userName = "John";
        Long businessId = 45l;
        String address = "address";
        String country = "country";
        String state = "state";
        Integer pincode = 4534436;
        String emailId = "email@gmail.com";
        Long contactNumber = 2424343554L;
        boolean isbusiness = true;
        String gender = "male";
        String status = "active";
        String password = "password";

        User user1 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        
        assertTrue(user1.equals(user1));
        assertEquals(user1.hashCode(), user1.hashCode());
        
        assertFalse(user1.equals(new Object()));
        assertFalse(user1.equals(null));
        
        User user2 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertTrue(user2.equals(user1));
        assertEquals(user2.hashCode(), user1.hashCode());

        user2 = build(3l, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, "userName1", businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = build(id, userName, 2342L, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = build(id, userName, businessId, "aaddress", country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = build(id, userName, businessId, address, "ccountry", state, pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = build(id, userName, businessId, address, country, "sstate", pincode, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = build(id, userName, businessId, address, country, state, 234234234, emailId, contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, "email@gmakm", contactNumber,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, emailId, 9876543221L,
                isbusiness, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                false, gender, status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, "dgender", status, password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, "sstatus", password);
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());
        
        user2 = build(id, userName, businessId, address, country, state, pincode, emailId, contactNumber,
                isbusiness, gender, status, "ppassword");
        assertFalse(user2.equals(user1));
        assertNotEquals(user2.hashCode(), user1.hashCode());

        user2 = new User();
        user1 = new User();

        assertTrue(user2.equals(user1));
        assertEquals(user2.hashCode(), user1.hashCode());

    }

    private User build(Long id, String userName, Long businessId, String address, String country, String state,
            Integer pincode, String emailId, Long contactNumber, boolean isbusiness, String gender, String status,
            String password) {
        User user = new User();
        user.setAccountStatus(status);
        user.setAddress(address);
        user.setBusinessId(businessId);
        user.setContactNumber(contactNumber);
        user.setCountry(country);
        user.setEmailId(emailId);
        user.setGender(gender);
        user.setId(id);
        user.setIsBusiness(isbusiness);
        user.setPassword(password);
        user.setPincode(pincode);
        user.setState(state);
        user.setUserName(userName);
        return user;
    }

    @Test
    public void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUserName("John");
        user.setBusinessId(123L);
        user.setAddress("123 Main St");
        user.setCountry("USA");
        user.setState("NY");
        user.setPincode(12345);
        user.setEmailId("john@example.com");
        user.setContactNumber(1234567890L);
        user.setIsBusiness(true);
        user.setGender("Male");
        user.setAccountStatus("Active");
        user.setPassword("password");

        String expectedString = "User(id=1, userName=John, businessId=123, address=123 Main St, country=USA, state=NY, pincode=12345, emailId=john@example.com, contactNumber=1234567890, isBusiness=true, gender=Male, accountStatus=Active, password=password)";
        assertEquals(expectedString, user.toString());
    }
}
