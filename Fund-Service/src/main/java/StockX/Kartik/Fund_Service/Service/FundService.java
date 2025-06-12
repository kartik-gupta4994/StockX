package StockX.Kartik.Fund_Service.Service;

import StockX.Kartik.Fund_Service.DataTransfer.FundTransaction;
import StockX.Kartik.Fund_Service.DataTransfer.TransactionType;
import StockX.Kartik.Fund_Service.Repository.FundTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundService {

    private final FundTransactionRepository fundRepo;

    @Transactional
    public void addFunds(String userId, double amount) {
        fundRepo.save(new FundTransaction(null, userId, amount, TransactionType.DEPOSIT, null));
    }

    @Transactional
    public void withdrawFunds(String userId, double amount) {
        double currentBalance = fundRepo.getBalance(userId);
        if (currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fundRepo.save(new FundTransaction(null, userId, -amount, TransactionType.WITHDRAW, null));
    }

    public double getBalance(String userId) {
        return Optional.ofNullable(fundRepo.getBalance(userId)).orElse(0.0);
    }
}

