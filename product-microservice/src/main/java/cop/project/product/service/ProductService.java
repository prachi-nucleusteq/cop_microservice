package cop.project.product.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cop.project.product.controller.ProductController;
import cop.project.product.dbo.Product;
import cop.project.product.dto.ProductDto;
import cop.project.product.dto.ResponseDto;
import cop.project.product.exception.UserNotFoundException;
import cop.project.product.repository.ProductRepository;
import cop.project.product.utils.Constants;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    public Product saveProduct(Product product) {
        product.setId(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE));
        product = productRepository.save(product);
        return product;
    }

    public ProductDto updateProduct(Integer productId, ProductDto productDto) throws UserNotFoundException {
        Product product = checkForProductId(productId);
        return prepareProductDto(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDto getProductById(Integer productId) throws UserNotFoundException {
        Product product = checkForProductId(productId);
        return prepareProductDto(product);
    }

    public Product checkForProductId(Integer productId) throws UserNotFoundException {
        Optional<Product> optinalProduct = productRepository.findById(productId);

        if (!optinalProduct.isPresent()) {
            LOGGER.error(String.format(Constants.PRODUCT_NOT_FOUND, productId));
            throw new UserNotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, productId));
        }
        return optinalProduct.get();
    }

    public ProductDto prepareProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setCurrency(product.getCurrency());
        productDto.setCurrencySymbol(product.getCurrencySymbol());
        productDto.setDiscount(product.getDiscount());
        productDto.setManufacturedBy(product.getManufacturedBy());
        productDto.setNumberOfProduct(product.getNumberOfProduct());
        productDto.setPrice(product.getPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductName(product.getProductName());
        return productDto;
    }

    public ResponseDto deleteProductById(Integer productId) throws UserNotFoundException {
        checkForProductId(productId);
        ResponseDto responseDto = new ResponseDto();

        productRepository.deleteById(productId);

        List<Product> productList = productRepository.findAll();

        responseDto.setMessage(String.format(Constants.PRODUCT_DELETED, productId));
        responseDto.setProductList(productList);

        return responseDto;
    }
}
