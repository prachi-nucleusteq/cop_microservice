package cop.project.product.controller;

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

import cop.project.product.dbo.Product;
import cop.project.product.dto.ProductDto;
import cop.project.product.dto.ResponseDto;
import cop.project.product.exception.UserNotFoundException;
import cop.project.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        log.info("Request recieved to saveProduct Controller {}", product.toString());
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{productId}")
    public ProductDto updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto)
            throws Exception {
        log.info("Request recieved to update Product Controller");
        return productService.updateProduct(productId, productDto);
    }

    @GetMapping("/get")
    public List<Product> getAllProduct() {
        log.info("Request recieved to getAllProduct Controller");
        return productService.getAllProducts();
    }

    @GetMapping("/get/{productId}")
    public ProductDto getProductById(@PathVariable Integer productId) throws UserNotFoundException {
        log.info("Request recieved to get product by id Controller");
        return productService.getProductById(productId);
    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseDto deleteProductById(@PathVariable Integer productId) throws UserNotFoundException {
        log.info("Request recieved to delete product by id Controller");
        return productService.deleteProductById(productId);
    }
}
