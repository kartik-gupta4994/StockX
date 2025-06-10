package StockX.Kartik.Portfolio_Service.DataTransfer;

import lombok.Data;

@Data
public class OrderDTO {
    private String stockSymbol;
    private int quantity;
    private double priceAtOrder;
    private String orderType;  // BUY or SELL
    private String status;
}

