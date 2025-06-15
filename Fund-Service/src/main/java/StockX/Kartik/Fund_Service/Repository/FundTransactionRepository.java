package StockX.Kartik.Fund_Service.Repository;

import StockX.Kartik.Fund_Service.DataTransfer.FundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FundTransactionRepository extends JpaRepository<FundTransaction, UUID> {
    @Query("SELECT SUM(f.amount) FROM FundTransaction f WHERE f.userId = :userId")
    Double getBalance(@Param("userId") long userId);
}

