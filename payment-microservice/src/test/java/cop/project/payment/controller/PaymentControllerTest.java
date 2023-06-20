package cop.project.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cop.project.payment.dbo.Wallet;
import cop.project.payment.dto.PaymentRequestDTO;
import cop.project.payment.service.PaymentService;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void addWalletTest() throws JsonProcessingException, Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(1200);
        wallet.setCardNumber("32113133");
        wallet.setDefault(false);
        wallet.setId(2);
        wallet.setUserId(3);

        when(paymentService.addWallet(wallet)).thenReturn(wallet);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/payment/wallet")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(wallet))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(wallet), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteWallet() {
        Integer walletId = 1;
        String expectedResponse = String.format("Wallet details removed successfully for %d", walletId);

        doNothing().when(paymentService).deleteWallet(1L);

        String response = paymentController.deleteWallet(1L);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testDefaultValue() throws JsonProcessingException, Exception {
        Long walletId = 123L;
        Long userId = 456L;
        Wallet expectedWallet = new Wallet();
        expectedWallet.setId(123);
        expectedWallet.setUserId(456);
        expectedWallet.setDefault(true);

        when(paymentService.setDefaultWallet(walletId, userId)).thenReturn(expectedWallet);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/payment/wallet/123/default/456")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(expectedWallet))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(expectedWallet), mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void testMakePayment() throws JsonProcessingException, Exception {
        Long walletId = 123L;
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setAmount(100.0);

        when(paymentService.makePayment(walletId, paymentRequestDTO.getAmount())).thenReturn("Payment done successfully. Balance in your account is 5684");

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/{walletId}", walletId).contentType("application/json")
                .content("{\"amount\": 100.0}")).andExpect(status().isOk())
                .andExpect(content().string("Payment done successfully. Balance in your account is 5684"));

        verify(paymentService, times(1)).makePayment(walletId, paymentRequestDTO.getAmount());
    }

}
