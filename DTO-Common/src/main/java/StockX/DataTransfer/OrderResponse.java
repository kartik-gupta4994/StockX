package StockX.DataTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String stockSymbol;
    private int quantity;
    private double priceAtOrder;
    private OrderType orderType;  // BUY or SELL
    private OrderStatus status;
}

