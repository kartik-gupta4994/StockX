package StockX.Kartik.Order_Service.DataTransfer;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private String stockSymbol;
    private int quantity;
    private double price;
    private OrderType type;
    private OrderStatus status;
    private LocalDateTime createdAt;
}

