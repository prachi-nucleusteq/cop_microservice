package cop.project.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cop.project.cart.dbo.CartItem;
import cop.project.cart.dto.ResponseOutDto;
import cop.project.cart.repository.CartRepository;
import cop.project.cart.utils.Constants;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.SUCCESS_ADD_MESSAGE);

        when(cartRepository.save(cartItem)).thenReturn(cartItem);
        when(sequenceGeneratorService.getSequenceNumber(anyString())).thenReturn(100);

        ResponseOutDto actualResponseOutDto = cartService.addToCart(cartItem);

        assertEquals(responseOutDto, actualResponseOutDto);

    }

    @Test
    public void testUpdatecart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.SUCCESS_UPDATED_MESSAGE);

        when(cartRepository.save(cartItem)).thenReturn(cartItem);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        ResponseOutDto actualResponseOutDto = cartService.updateCartItem(1L, cartItem);
        assertEquals(responseOutDto, actualResponseOutDto);
    }

    @Test
    public void testDeletecart() throws Exception {
        Long cartId = 7L;
        CartItem cartItem = new CartItem();
        cartItem.setId(7);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.PRODUCT_DELETED_SUCCESSFULLY);

        when(cartRepository.findById(7L)).thenReturn(Optional.of(cartItem));
        doNothing().when(cartRepository).deleteById(7L);

        ResponseOutDto actualResponseOutDto = cartService.deleteCartItem(cartId);
        assertEquals(responseOutDto, actualResponseOutDto);

    }

    @Test
    public void testgetAllCartItems() throws Exception {
        List<CartItem> cartItemList = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setProductName("product name");
        cartItem.setQuantity(6);

        cartItemList.add(cartItem);

        when(cartRepository.findAll()).thenReturn(cartItemList);

        List<CartItem> actualCartItemList = cartService.getAllCartItems();

        assertEquals(cartItemList, actualCartItemList);

    }
}
