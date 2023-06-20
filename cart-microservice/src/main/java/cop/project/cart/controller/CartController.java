package cop.project.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cop.project.cart.dbo.CartItem;
import cop.project.cart.dto.ResponseOutDto;
import cop.project.cart.exception.NotFoundException;
import cop.project.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addProduct")
    public ResponseOutDto addToCart(@RequestBody CartItem cartItem) {
        log.info("Request recieved to add cart controller {}", cartItem.toString());
        ResponseOutDto responseOutDto = cartService.addToCart(cartItem);
        return responseOutDto;
    }

    @PutMapping("/updateCartItem/{id}")
    public ResponseOutDto updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) throws NotFoundException {
        log.info("Request recieved to update cart controller {}", updatedCartItem.toString());
        ResponseOutDto ResponseOutDto = cartService.updateCartItem(id, updatedCartItem);
        return ResponseOutDto;
    }

    @DeleteMapping("/deleteCartItem/{id}")
    public ResponseOutDto deleteCartItem(@PathVariable Long id) throws NotFoundException {
        log.info("Request recieved to delete cart controller {}", id);
        ResponseOutDto responseOutDto = cartService.deleteCartItem(id);
        return responseOutDto;
    }

    @GetMapping("/getCartProduct")
    public List<CartItem> getAllCartItems() {
        log.info("Request recieved to get all  cart product controller {}");
        return cartService.getAllCartItems();
    }
}
