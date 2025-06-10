package StockX.Kartik.Portfolio_Service.DataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PortfolioSummary {
    private String stockSymbol;
    private int quantity;
    private double averagePrice;
}


