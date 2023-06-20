package cop.project.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cop.project.order.dbo.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findByBuyerId(Long buyerId);
}
