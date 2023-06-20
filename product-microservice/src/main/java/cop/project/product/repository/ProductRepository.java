package cop.project.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cop.project.product.dbo.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{

}
