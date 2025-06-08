package StockX.Kartik.User_Service.DataTransferObject;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
}
