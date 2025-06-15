package StockX.Kartik.Stock_Service.StockService;

import StockX.Kartik.Stock_Service.DataAccess.StockDAO;
import StockX.Kartik.Stock_Service.DataTransfer.Stock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class StockService {

    @Autowired
    private StockDAO stockDAO;

    private final Random random = new Random();

    public List<Stock> getAllStocks() {
        return stockDAO.findAll();
    }

    public Stock getStockById(UUID id) {
        return stockDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public Stock getStockBySymbol(String symbol) {
        return stockDAO.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public void loadStocks() throws IOException {

        ObjectMapper mapper = new ObjectMapper();//TO convert JSON data to java objects

        //generic type info (like List<Stock>) is erased at runtime in Java, so need TypeReference
        TypeReference<List<Stock>> typeRef = new TypeReference<List<Stock>>() {};
        InputStream inputStream = new ClassPathResource("stocks.json").getInputStream();

        List<Stock> stocks = mapper.readValue(inputStream, typeRef);

        stockDAO.saveAll(stocks);
    }

    @Scheduled(fixedRate = 10000) // Every 10 seconds
    public void updateStockPricesMock() {
        List<Stock> stocks = stockDAO.findAll();

        for (Stock stock : stocks) {
            BigDecimal currentPrice = stock.getPrice();

            // Random percentage between -5% to +5%
            double changePercent = (random.nextDouble() * 10) - 5;
            BigDecimal change = currentPrice.multiply(BigDecimal.valueOf(changePercent / 100));

            BigDecimal newPrice = currentPrice.add(change).setScale(2, RoundingMode.HALF_UP);
            stock.setPrice(newPrice);
            stock.setLastUpdated(LocalDateTime.now());
        }

        stockDAO.saveAll(stocks);
        System.out.println("✅ Prices updated at " + LocalDateTime.now());
    }
}

