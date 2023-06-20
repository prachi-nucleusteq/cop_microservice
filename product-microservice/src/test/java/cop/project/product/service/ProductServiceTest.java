package cop.project.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.fasterxml.jackson.core.JsonProcessingException;

import cop.project.product.dbo.Product;
import cop.project.product.dto.ProductDto;
import cop.project.product.dto.ResponseDto;
import cop.project.product.repository.ProductRepository;
import cop.project.product.utils.Constants;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveProductTest() throws JsonProcessingException, Exception {

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        when(productRepository.save(product)).thenReturn(product);
        when(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE)).thenReturn(1);

        Product expectedProduct = productService.saveProduct(product);
        assertEquals(expectedProduct, product);
    }

    @Test
    public void updateProductTest() throws JsonProcessingException, Exception {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("product name");
        productDto.setProductDescription("product description");
        productDto.setPrice(23.5);
        productDto.setNumberOfProduct(3);
        productDto.setManufacturedBy("manufactured by");
        productDto.setDiscount("12%");
        productDto.setCurrencySymbol("$");
        productDto.setCurrency("USA");

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        when(productRepository.save(product)).thenReturn(product);
        when(productRepository.findById(2)).thenReturn(Optional.of(product));

        ProductDto expectedProductDto = productService.updateProduct(2, productDto);
        assertEquals(expectedProductDto, productDto);
    }

    @Test
    public void getAllProductTest() throws JsonProcessingException, Exception {

        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        productList.add(product);

        Product product1 = new Product();
        product1.setProductName("name");
        product1.setProductDescription("description");
        product1.setPrice(23.5);
        product1.setNumberOfProduct(3);
        product1.setManufacturedBy("manufactured");
        product1.setId(3);
        product1.setDiscount("15%");
        product1.setCurrencySymbol("$");
        product1.setCurrency("USA");

        productList.add(product1);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> expectedProductList = productService.getAllProducts();
        assertEquals(expectedProductList, productList);
    }

    @Test
    public void getProductByIdTest() throws JsonProcessingException, Exception {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("product name");
        productDto.setProductDescription("product description");
        productDto.setPrice(23.5);
        productDto.setNumberOfProduct(3);
        productDto.setManufacturedBy("manufactured by");
        productDto.setDiscount("12%");
        productDto.setCurrencySymbol("$");
        productDto.setCurrency("USA");

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        when(productRepository.save(product)).thenReturn(product);
        when(productRepository.findById(2)).thenReturn(Optional.of(product));

        ProductDto expectedProductDto = productService.getProductById(2);
        assertEquals(expectedProductDto, productDto);
    }

    @Test
    public void deleteProductByIdTest() throws JsonProcessingException, Exception {

        Product product = new Product();
        product.setProductName("product name");
        product.setProductDescription("product description");
        product.setPrice(23.5);
        product.setNumberOfProduct(3);
        product.setManufacturedBy("manufactured by");
        product.setId(2);
        product.setDiscount("12%");
        product.setCurrencySymbol("$");
        product.setCurrency("USA");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(String.format(Constants.PRODUCT_DELETED, 1));

        doNothing().when(productRepository).deleteById(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseDto expectedResponseDto = productService.deleteProductById(1);
        assertEquals(expectedResponseDto, responseDto);
    }
}
