package cop.project.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cop.project.cart.dbo.CartItem;
import cop.project.cart.dto.ResponseOutDto;
import cop.project.cart.exception.NotFoundException;
import cop.project.cart.repository.CartRepository;
import cop.project.cart.utils.Constants;
import cop.project.cart.utils.ErrorConstants;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public ResponseOutDto addToCart(CartItem cartItem) {
        cartItem.setId(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE));
        cartRepository.save(cartItem);
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.SUCCESS_ADD_MESSAGE);
        return responseOutDto;
    }


    public ResponseOutDto updateCartItem(Long id, CartItem updatedCartItem) throws NotFoundException {
        CartItem cartItem = validateCartId(id);

        cartItem.setProductName(updatedCartItem.getProductName());
        cartItem.setQuantity(updatedCartItem.getQuantity());
        cartRepository.save(cartItem);
        ResponseOutDto ResponseOutDto = new ResponseOutDto();
        ResponseOutDto.setMessage(Constants.SUCCESS_UPDATED_MESSAGE);
        return ResponseOutDto;
    }
    
    public CartItem validateCartId(Long id) throws NotFoundException
    {
        Optional<CartItem> optionalCartItem = cartRepository.findById(id);
        if (!optionalCartItem.isPresent()) {
            log.info(String.format(ErrorConstants.INVALID_CART_ID + ",throwing NotFoundException ", id));
            throw new NotFoundException(String.format(ErrorConstants.INVALID_CART_ID, id));
        }
        return optionalCartItem.get();
    }


    public ResponseOutDto deleteCartItem(Long id) throws NotFoundException {
        validateCartId(id);
        cartRepository.deleteById(id);
        ResponseOutDto ResponseOutDto = new ResponseOutDto();
        ResponseOutDto.setMessage(Constants.PRODUCT_DELETED_SUCCESSFULLY);
        return ResponseOutDto;
    }


    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }
}
