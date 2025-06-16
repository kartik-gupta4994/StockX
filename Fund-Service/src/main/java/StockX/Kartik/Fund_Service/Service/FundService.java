package StockX.Kartik.Fund_Service.Service;

import StockX.DataTransfer.OrderPlaceResponse;
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
    public void addFunds(long userId, double amount) {
        fundRepo.save(new FundTransaction(null, userId, amount, TransactionType.DEPOSIT, null));
    }

    @Transactional
    public OrderPlaceResponse withdrawFunds(long userId, double amount) {
        double currentBalance = fundRepo.getBalance(userId);
        if (currentBalance < amount)
            return  new OrderPlaceResponse(false, "Insufficient balance");

        fundRepo.save(new FundTransaction(null, userId, -amount, TransactionType.WITHDRAW, null));
        return new OrderPlaceResponse(true, "Funds withdrawn successfully.");
    }

    public double getBalance(long userId) {
        return Optional.ofNullable(fundRepo.getBalance(userId)).orElse(0.0);
    }
}

