package StockX.Kartik.Authentication_Service.DataTransfer;

import lombok.Data;

// in auth-service
@Data
public class UserProfileRequest {
    private String username;
    private String name;
    private String email;
}

