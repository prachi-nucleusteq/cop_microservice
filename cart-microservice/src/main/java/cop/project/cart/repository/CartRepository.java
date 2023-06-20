package cop.project.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cop.project.cart.dbo.CartItem;


public interface CartRepository extends MongoRepository<CartItem, Long> {

}
