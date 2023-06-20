package cop.project.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;

import cop.project.payment.constants.Constants;
import cop.project.payment.dbo.Wallet;
import cop.project.payment.exception.InsufficientBalanceException;
import cop.project.payment.repository.WalletRepository;

public class PaymentServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addWalletTest() throws JsonProcessingException, Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(1200);
        wallet.setCardNumber("32113133");
        wallet.setDefault(false);
        wallet.setId(1);
        wallet.setUserId(3);

        when(walletRepository.save(wallet)).thenReturn(wallet);
        when(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE)).thenReturn(1);

        Wallet actualWallet = paymentService.addWallet(wallet);

        assertEquals(wallet, actualWallet);
    }

    @Test
    public void deleteWallet() {
        Long walletId = 123L;

        doNothing().when(walletRepository).deleteById(walletId);

        paymentService.deleteWallet(walletId);

        verify(walletRepository, times(1)).deleteById(walletId);
    }

    @Test
    public void testMakePayment() throws InsufficientBalanceException {
        Long walletId = 123L;
        double amount = 50.0;
        double initialBalance = 100.0;

        Wallet wallet = new Wallet();
        wallet.setBalance(100.0);
        wallet.setDefault(false);
        wallet.setId(123);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        when(walletRepository.save(wallet)).thenReturn(wallet);

        String result = paymentService.makePayment(walletId, amount);

        double expectedBalance = initialBalance - amount;
        String expectedMessage = String.format("Payment done successfully. Balance in your account is 50.0",
                expectedBalance);

        assertEquals(expectedMessage, result);
        assertEquals(expectedBalance, wallet.getBalance(), 0.01);

        verify(walletRepository, times(1)).findById(walletId);
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    public void testSetDefaultWallet_Success() {
        Long walletId = 123L;
        Long userId = 456L;

        Query query = new Query(Criteria.where("userId").is(userId).and("isDefault").is(false));
        Wallet defaultWallet = new Wallet();
        defaultWallet.setBalance(1200);
        defaultWallet.setCardNumber("32113133");
        defaultWallet.setDefault(false);
        defaultWallet.setId(123);
        defaultWallet.setUserId(456);

        when(mongoTemplate.findOne(query, Wallet.class)).thenReturn(defaultWallet);
        when(walletRepository.save(defaultWallet)).thenReturn(defaultWallet);

        Wallet result = paymentService.setDefaultWallet(walletId, userId);

        defaultWallet.setDefault(true);

        verify(mongoTemplate, times(1)).findOne(query, Wallet.class);
        verify(walletRepository, times(1)).save(defaultWallet);

        assertEquals(defaultWallet, result);
        assertTrue(result.isDefault());
}
}
