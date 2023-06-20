package cop.project.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cop.project.payment.dbo.Wallet;


public interface WalletRepository extends MongoRepository<Wallet, Long>{

}
