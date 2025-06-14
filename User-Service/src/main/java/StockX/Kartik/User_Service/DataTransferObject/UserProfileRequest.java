package StockX.Kartik.User_Service.DataTransferObject;

import lombok.Data;

// in auth-service
@Data
public class UserProfileRequest {
    private String username;
    private String name;
    private String email;
}

