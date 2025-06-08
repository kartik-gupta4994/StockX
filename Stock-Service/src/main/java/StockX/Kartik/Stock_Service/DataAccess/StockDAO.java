package StockX.Kartik.Stock_Service.DataAccess;

import StockX.Kartik.Stock_Service.DataTransfer.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockDAO extends JpaRepository<Stock, UUID> {
    Optional<Stock> findBySymbol(String symbol);
}

