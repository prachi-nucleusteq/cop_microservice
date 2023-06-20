package cop.project.order.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cop.project.order.constants.Constants;
import cop.project.order.dbo.Order;
import cop.project.order.dto.ResponseOutDto;
import cop.project.order.exception.NotFoundException;
import cop.project.order.repository.OrderRepository;
import cop.project.order.service.OrderService;
import cop.project.order.service.SequenceGeneratorService;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setBuyerId(1);
        order.setId(1);
        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_SAVED);

        when(sequenceGeneratorService.getSequenceNumber(anyString())).thenReturn(100);

        when(orderRepository.save(order)).thenReturn(order);

        ResponseOutDto result = orderService.placeOrder(order);

        assertEquals(responseOutDto, result);
        verify(sequenceGeneratorService).getSequenceNumber(anyString());
        verify(orderRepository).save(order);
    }

    @Test
    public void testCancelOrder() throws NotFoundException {
        Order order = new Order();
        order.setBuyerId(1);
        order.setId(1);
        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_CANCELED);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).deleteById(1L);

        ResponseOutDto result = orderService.cancelOrder(1L);

        assertEquals(responseOutDto, result);

    }

    @Test
    public void testGetOrdersByBuyerId() {
        Order order = new Order();
        order.setId(1);
        order.setBuyerId(10);

        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);

        List<Order> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(order);

        when(orderRepository.findByBuyerId(1L)).thenReturn(expectedOrderList);

        List<Order> actualOrderList = orderService.getOrdersByBuyerId(1L);

        assertEquals(expectedOrderList, actualOrderList);

    }

    @Test
    public void testGetOrdersByOrderId() throws NotFoundException {

        Order order = new Order();
        order.setBuyerId(1);
        order.setId(1);
        List<String> product = new ArrayList<>();
        product.add("Mobile");
        product.add("Tab");
        order.setProducts(product);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order actualOrder = orderService.getOrderById(1L);

        assertEquals(order, actualOrder);

    }
}
