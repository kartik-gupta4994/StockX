package StockX.Kartik.User_Service.DataTransferObject;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
