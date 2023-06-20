package cop.project.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cop.project.order.constants.Constants;
import cop.project.order.dbo.Order;
import cop.project.order.dto.ResponseOutDto;
import cop.project.order.exception.NotFoundException;
import cop.project.order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public ResponseOutDto placeOrder(Order order) {
        order.setId(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE));
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_SAVED);
        orderRepository.save(order);
        return responseOutDto;
    }

    public ResponseOutDto cancelOrder(Long orderId) throws NotFoundException {
        validateOrderId(orderId);

        orderRepository.deleteById(orderId);
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(Constants.ORDER_CANCELED);
        return responseOutDto;
    }

    private Order validateOrderId(Long orderId) throws NotFoundException {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new NotFoundException(String.format(Constants.INVALID_ID, orderId));
        }
        return optionalOrder.get();
    }

    public List<Order> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public Order getOrderById(Long orderId) throws NotFoundException {
        Order order = validateOrderId(orderId);
        return order;
    }
}
