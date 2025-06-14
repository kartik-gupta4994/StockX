package StockX.Kartik.User_Service.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private String username;
    private String email;
    private BigDecimal walletBalance;
}

