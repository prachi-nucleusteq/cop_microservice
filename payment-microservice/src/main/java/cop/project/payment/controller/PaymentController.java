package cop.project.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cop.project.payment.dbo.Wallet;
import cop.project.payment.dto.PaymentRequestDTO;
import cop.project.payment.exception.InsufficientBalanceException;
import cop.project.payment.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/wallet")
    public Wallet addWallet(@RequestBody Wallet wallet) {
        return paymentService.addWallet(wallet);
    }

    @DeleteMapping("/wallet/{walletId}")
    public String deleteWallet(@PathVariable Long walletId) {
        paymentService.deleteWallet(walletId);
        return String.format("Wallet details removed successfully for %d", walletId);
    }

    @PostMapping("/{walletId}")
    public String makePayment(@PathVariable Long walletId, @RequestBody PaymentRequestDTO paymentRequestDTO) throws InsufficientBalanceException {
        String response = paymentService.makePayment(walletId, paymentRequestDTO.getAmount());
        return response;
    }

    @PutMapping("/wallet/{walletId}/default/{userId}")
    public Wallet setDefaultWallet(@PathVariable Long walletId, @PathVariable Long userId) {
        return paymentService.setDefaultWallet(walletId, userId);
    }
}

