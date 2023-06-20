package cop.project.user.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BusinessTest {

    @Test
    public void testGetterSetter() {

        Business business = new Business();

        assertNull(business.getId());
        Long id = 2L;
        business.setId(id);
        assertEquals(id, business.getId());

        assertNull(business.getEmailId());
        String email = "email@gmail.com";
        business.setEmailId(email);
        assertEquals(email, business.getEmailId());

        assertNull(business.getBusinessName());
        String businessName = "business name";
        business.setBusinessName(businessName);
        assertEquals(businessName, business.getBusinessName());

        assertNull(business.getBusinesscontactNumber());
        Long businessContactNumber = 9876543210L;
        business.setBusinesscontactNumber(businessContactNumber);
        assertEquals(businessContactNumber, business.getBusinesscontactNumber());

        assertNull(business.getBusinessAddress());
        String businessAddress = "address";
        business.setBusinessAddress(businessAddress);
        assertEquals(businessAddress, business.getBusinessAddress());

        assertEquals(
                "Business(id=2, businessName=business name, businessAddress=address, emailId=email@gmail.com, businesscontactNumber=9876543210)",
                business.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Long id = 2L;
        String email = "email@gmail.com";
        String businessName = "business name";
        Long businessContactNumber = 9876543210L;
        String businessAddress = "address";
        

        Business business1 = build(id, email, businessName, businessContactNumber, businessAddress);

        assertTrue(business1.equals(business1));
        assertEquals(business1.hashCode(), business1.hashCode());

        assertFalse(business1.equals(new Object()));
        assertFalse(business1.equals(null));

        Business business2 = build(id, "email@gmail.comm", businessName, businessContactNumber, businessAddress);
        assertFalse(business2.equals(business1));
        assertEquals(business2.hashCode(), business2.hashCode());

        business2 = build(3L, email, businessName, businessContactNumber, businessAddress);
        assertFalse(business2.equals(business1));
        assertNotEquals(business2.hashCode(), business1.hashCode());

        business2 = build(id, email, "businessName", businessContactNumber, businessAddress);
        assertFalse(business2.equals(business1));
        assertNotEquals(business2.hashCode(), business1.hashCode());
        
        business2 = build(id, email, businessName, 435555531L, businessAddress);
        assertFalse(business2.equals(business1));
        assertNotEquals(business2.hashCode(), business1.hashCode());

        business2 = build(id, email, businessName, businessContactNumber, "businessAdress");
        assertFalse(business2.equals(business1));
        assertNotEquals(business2.hashCode(), business1.hashCode());

        business2 = new Business();
        business1 = new Business();

        assertTrue(business2.equals(business1));
        assertEquals(business2.hashCode(), business1.hashCode());
    }

    private Business build(Long id, String email, String businessName, Long businessContactNumber,
            String businessAddress) {
        Business business = new Business();
        business.setId(id);
        business.setEmailId(email);
        business.setBusinessName(businessName);
        business.setBusinesscontactNumber(businessContactNumber);
        business.setBusinessAddress(businessAddress);
        return business;
    }

   
}
