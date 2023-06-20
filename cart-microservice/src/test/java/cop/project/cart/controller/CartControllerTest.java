package cop.project.cart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cop.project.cart.dbo.CartItem;
import cop.project.cart.dto.ResponseOutDto;
import cop.project.cart.service.CartService;
import cop.project.cart.utils.Constants;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    CartController cartController;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testAddToCart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.SUCCESS_ADD_MESSAGE);

        when(cartService.addToCart(cartItem)).thenReturn(responseOutDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cart/addProduct")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECTMAPPPER.writeValueAsBytes(cartItem))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDto), mvcResult.getResponse().getContentAsString());

        verify(cartService, times(1)).addToCart(cartItem);
    }

    @Test
    public void testUpdatecart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.SUCCESS_UPDATED_MESSAGE);

        when(cartService.updateCartItem(1l, cartItem)).thenReturn(responseOutDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cart/updateCartItem/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECTMAPPPER.writeValueAsBytes(cartItem))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDto), mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testDeletecart() throws Exception {
        Long cartId = 7L;

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.PRODUCT_DELETED_SUCCESSFULLY);

        when(cartService.deleteCartItem(cartId)).thenReturn(responseOutDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cart/deleteCartItem/{id}", 7)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .header("authorization", "authorization");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDto), mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testgetAllCartItems() throws Exception {
        List<CartItem> cartItemList = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        cartItemList.add(cartItem);

        when(cartService.getAllCartItems()).thenReturn(cartItemList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/getCartProduct")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(cartItemList), mvcResult.getResponse().getContentAsString());
    }
}
