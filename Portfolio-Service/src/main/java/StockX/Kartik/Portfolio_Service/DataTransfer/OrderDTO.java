package StockX.Kartik.Portfolio_Service.DataTransfer;

import StockX.DataTransfer.OrderStatus;
import StockX.DataTransfer.OrderType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class OrderDTO {
    private String stockSymbol;
    private int quantity;
    private double priceAtOrder;
    private OrderType orderType;  // BUY or SELL
    private OrderStatus status;

}

