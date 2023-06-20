package cop.project.user.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void testGetterSetter() {

        Location location = new Location();

        assertNull(location.getId());
        Long id = 2L;
        location.setId(id);
        assertEquals(id, location.getId());

        assertNull(location.getBusinessId());
        Long businessid = 3L;
        location.setBusinessId(businessid);
        assertEquals(businessid, location.getBusinessId());

        assertNull(location.getCountry());
        String country = "country";
        location.setCountry(country);
        assertEquals(country, location.getCountry());

        assertNull(location.getLocation());
        String locationn = "location";
        location.setLocation(locationn);
        assertEquals(locationn, location.getLocation());

        assertNull(location.getPincode());
        Integer pincode = 2424433;
        location.setPincode(pincode);
        assertEquals(pincode, location.getPincode());

        assertNull(location.getState());
        String state = "state";
        location.setState(state);
        assertEquals(state, location.getState());

        assertEquals("Location(id=2, businessId=3, location=location, state=state, country=country, pincode=2424433)",
                location.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Long id = 2L;
        Long businessid = 3L;
        String country = "country";
        String locationn = "location";
        Integer pincode = 2424433;
        String state = "state";

        Location location1 = build(id, businessid, country, locationn, pincode, state);

        assertTrue(location1.equals(location1));
        assertEquals(location1.hashCode(), location1.hashCode());

        assertFalse(location1.equals(new Object()));
        assertFalse(location1.equals(null));

        Location location2 = build(id, businessid, country, locationn, pincode, state);
        assertTrue(location2.equals(location1));
        assertEquals(location2.hashCode(), location1.hashCode());

        location2 = build(10L, businessid, country, locationn, pincode, state);
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = build(id, 11L, country, locationn, pincode, state);
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = build(id, businessid, "ccountry", locationn, pincode, state);
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = build(id, businessid, country, "location1", pincode, state);
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = build(id, businessid, country, locationn, 242442, state);
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = build(id, businessid, country, locationn, pincode, "state1");
        assertFalse(location2.equals(location1));
        assertNotEquals(location2.hashCode(), location1.hashCode());

        location2 = new Location();
        location1 = new Location();

        assertTrue(location2.equals(location1));
        assertEquals(location2.hashCode(), location1.hashCode());
    }

    private Location build(Long id, Long businessid, String country, String locationn, Integer pincode, String state) {
        Location location = new Location();
        location.setBusinessId(businessid);
        location.setId(id);
        location.setCountry(country);
        location.setLocation(locationn);
        location.setPincode(pincode);
        location.setState(state);

        return location;
    }

}
