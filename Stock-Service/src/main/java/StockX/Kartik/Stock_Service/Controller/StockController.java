package StockX.Kartik.Stock_Service.Controller;

import StockX.Kartik.Stock_Service.DataTransfer.Stock;
import StockX.Kartik.Stock_Service.StockService.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable UUID id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    // Optional: get by symbol
    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<Stock> getBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(stockService.getStockBySymbol(symbol));
    }

    @GetMapping("/loadStocks")
    public ResponseEntity<String> loadStocks() throws IOException {
        stockService.loadStocks();
        return ResponseEntity.ok("Stock Data Loaded");
    }
}

