package StockX.Kartik.Authentication_Service.DataTransfer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    private String username;
    private String password;
    // profile info
    private String name;
    private String email;

}
