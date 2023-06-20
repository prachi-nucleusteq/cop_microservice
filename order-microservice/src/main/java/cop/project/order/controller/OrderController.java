package cop.project.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cop.project.order.dbo.Order;
import cop.project.order.dto.ResponseOutDto;
import cop.project.order.exception.NotFoundException;
import cop.project.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseOutDto placeOrder(@RequestBody Order order) {
        log.info("Request recieved to placeOrder Controller {}", order.toString());
        ResponseOutDto responseOutDto = orderService.placeOrder(order);
        return responseOutDto;
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseOutDto cancelOrder(@PathVariable Long orderId) throws NotFoundException {
        log.info("Request recieved to delete the Order by Id Controller {}", orderId);
        ResponseOutDto responseOutDto = orderService.cancelOrder(orderId);
        return responseOutDto;
    }

    @GetMapping("/getBuyer/{buyerId}")
    public List<Order> getOrdersByBuyerId(@PathVariable Long buyerId) {
        log.info("Request recieved to get the buyer Controller {}", buyerId);
        return orderService.getOrdersByBuyerId(buyerId);
    }

    @GetMapping("order/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) throws NotFoundException {
        log.info("Request recieved to get the order by Id Controller {}", orderId);
        Order order = orderService.getOrderById(orderId);
        return order;
    }
}
