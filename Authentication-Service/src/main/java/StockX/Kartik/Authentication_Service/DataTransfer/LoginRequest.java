package StockX.Kartik.Authentication_Service.DataTransfer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull
    private String username;
    private String password;
}
