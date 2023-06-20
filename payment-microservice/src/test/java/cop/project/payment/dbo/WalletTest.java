package cop.project.payment.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class WalletTest {

    @Test
    public void walletObject_SettersAndGetters_WorkAsExpected() {
        Integer id = 1;
        Integer userId = 123;
        String cardNumber = "1234567890123456";
        double balance = 100.0;
        boolean isDefault = true;

        Wallet wallet = new Wallet();
        wallet.setId(id);
        wallet.setUserId(userId);
        wallet.setCardNumber(cardNumber);
        wallet.setBalance(balance);
        wallet.setDefault(isDefault);

        assertEquals(id, wallet.getId());
        assertEquals(userId, wallet.getUserId());
        assertEquals(cardNumber, wallet.getCardNumber());
        assertEquals(balance, wallet.getBalance());
        assertEquals(isDefault, wallet.isDefault());
    }

    @Test
    public void testToString() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId(123);
        wallet.setCardNumber("1234567890123456");
        wallet.setBalance(100.0);
        wallet.setDefault(true);

        String expectedToString = "Wallet(id=1, userId=123, cardNumber=1234567890123456, balance=100.0, isDefault=true)";

        String actualToString = wallet.toString();

        assertEquals(expectedToString, actualToString);
    }
    @Test
    public void equalsAndHashCode() {
        Wallet wallet1 = new Wallet();
        wallet1.setId(1);
        wallet1.setUserId(123);
        wallet1.setCardNumber("1234567890123456");
        wallet1.setBalance(100.0);
        wallet1.setDefault(true);

        Wallet wallet2 = new Wallet();
        wallet2.setId(1);
        wallet2.setUserId(123);
        wallet2.setCardNumber("1234567890123456");
        wallet2.setBalance(100.0);
        wallet2.setDefault(true);

        Wallet wallet3 = new Wallet();
        wallet3.setId(2);
        wallet3.setUserId(456);
        wallet3.setCardNumber("9876543210987654");
        wallet3.setBalance(200.0);
        wallet3.setDefault(false);

        assertEquals(wallet1, wallet2);
        assertNotEquals(wallet1, wallet3);
        assertEquals(wallet1.hashCode(), wallet2.hashCode());
        assertNotEquals(wallet1.hashCode(), wallet3.hashCode());
    }
}
