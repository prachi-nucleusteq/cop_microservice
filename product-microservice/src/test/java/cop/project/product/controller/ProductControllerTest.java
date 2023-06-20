package cop.project.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cop.project.product.dbo.Product;
import cop.project.product.dto.ProductDto;
import cop.project.product.dto.ResponseDto;
import cop.project.product.repository.ProductRepository;
import cop.project.product.service.ProductService;
import cop.project.product.utils.Constants;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
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

        when(productService.saveProduct(product)).thenReturn(product);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/signUp")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(product))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(product), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateProductByIdTest() throws JsonProcessingException, Exception {

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

        when(productService.updateProduct(2, productDto)).thenReturn(productDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/update/{productId}", 2)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(productDto))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(productDto), mvcResult.getResponse().getContentAsString());
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

        when(productService.getAllProducts()).thenReturn(productList);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/get").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).header("user-agent", "userAgent"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(productList), mvcResult.getResponse().getContentAsString());
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

        when(productService.getProductById(2)).thenReturn(productDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/update/{productId}", 2)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(productDto))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(productDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteProductByIdTest() throws JsonProcessingException, Exception {

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

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(String.format(Constants.PRODUCT_DELETED, 1));
        responseDto.setProductList(productList);

        when(productService.deleteProductById(2)).thenReturn(responseDto);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.delete("/delete/{productId}", 1).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).header("user-agent", "userAgent"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseDto), mvcResult.getResponse().getContentAsString());
    }

}
