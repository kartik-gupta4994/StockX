package StockX.Kartik.Order_Service.DataTransfer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import StockX.DataTransfer.OrderType;
import lombok.Data;

@Data
public class PlaceOrderRequest {

    @NotBlank
    private String stockSymbol;

    @Min(1)
    private int quantity;

    @DecimalMin("0.01")
    private double price;

    @NotNull
    private OrderType type;

    // getters and setters
}
