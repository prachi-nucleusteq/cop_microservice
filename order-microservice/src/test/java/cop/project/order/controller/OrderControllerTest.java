package cop.project.order.controller;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cop.project.order.constants.Constants;
import cop.project.order.dbo.Order;
import cop.project.order.dto.ResponseOutDto;
import cop.project.order.service.OrderService;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testPlaceOrder() throws JsonProcessingException, Exception {
        Order order = new Order();
        order.setBuyerId(1);
        order.setId(1);
        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_SAVED);
        when(orderService.placeOrder(order)).thenReturn(responseOutDto);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/order/place").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(OBJECTMAPPPER.writeValueAsBytes(order)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testCancelOrder() throws Exception {

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_CANCELED);

        when(orderService.cancelOrder(10L)).thenReturn(responseOutDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/order/delete/10")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .header("authorization", "authorization");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetOrderByBuyerId() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setBuyerId(10);

        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);

        List<Order> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(order);

        when(orderService.getOrdersByBuyerId(10L)).thenReturn(expectedOrderList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/getBuyer/10")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(expectedOrderList), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setBuyerId(1);
        order.setId(1);
        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        when(orderService.getOrderById(1L)).thenReturn(order);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/order/1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(order), mvcResult.getResponse().getContentAsString());
    }
}
