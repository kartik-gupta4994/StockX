package StockX.Kartik.Stock_Service.StockService;

import StockX.Kartik.Stock_Service.DataAccess.StockDAO;
import StockX.Kartik.Stock_Service.DataTransfer.Stock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor()
public class StockService {

    private final StockDAO stockDAO;
    private final RestTemplate restTemplate;

    @Value("${Api_Key}")
    private String API_Key;

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
        TypeReference<List<Stock>> typeRef = new TypeReference<>() {};
        InputStream inputStream = new ClassPathResource("stocks.json").getInputStream();

        List<Stock> stocks = mapper.readValue(inputStream, typeRef);

        stockDAO.saveAll(stocks);
    }

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void updateStockPrices() {
        List<Stock> stocks = stockDAO.findAll();

        for (Stock stock : stocks) {
            try {
                String symbol = stock.getSymbol();  // e.g. "AAPL" or "TCS.BSE"
                String url = "https://api.twelvedata.com/price?symbol=" + symbol + "&apikey=" + API_Key;

                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                Map data = response.getBody();

                if (data != null && data.containsKey("price")) {
                    double newPrice = Double.parseDouble((String) data.get("price"));
                    stock.setPrice(BigDecimal.valueOf(newPrice));
                    stockDAO.save(stock);
                } else {
                    System.out.println("Could not fetch price for " + symbol + ": " + data);
                }
            } catch (Exception e) {
                System.out.println("Error updating " + stock.getSymbol() + ": " + e.getMessage());
            }
        }
    }
}

