package cop.project.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cop.project.payment.constants.Constants;
import cop.project.payment.dbo.Wallet;
import cop.project.payment.exception.InsufficientBalanceException;
import cop.project.payment.repository.WalletRepository;


@Service
public class PaymentService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Wallet addWallet(Wallet wallet) {
        wallet.setId(sequenceGeneratorService.getSequenceNumber(Constants.PRODUCT_SEQUENCE));
        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long walletId) {
        walletRepository.deleteById(walletId);
    }

    public String makePayment(Long walletId, double amount) throws InsufficientBalanceException {
        Wallet wallet = walletRepository.findById(walletId).get();

        if (wallet.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in the wallet");
        }
        double newBalance = wallet.getBalance() - amount;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
        return String.format("Payment done successfully. Balance in your account is %s", newBalance);
    }

    public Wallet setDefaultWallet(Long walletId, Long userId) {
        Query query = new Query(Criteria.where("userId").is(userId).and("isDefault").is(false));
        Wallet defaultWallet = mongoTemplate.findOne(query, Wallet.class);

        defaultWallet.setDefault(true);
        return walletRepository.save(defaultWallet);
    }

}
