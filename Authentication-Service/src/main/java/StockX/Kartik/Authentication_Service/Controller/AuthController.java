package StockX.Kartik.Authentication_Service.Controller;

import StockX.Kartik.Authentication_Service.DataTransfer.AuthResponse;
import StockX.Kartik.Authentication_Service.DataTransfer.LoginRequest;
import StockX.Kartik.Authentication_Service.DataTransfer.RegisterRequest;
import StockX.Kartik.Authentication_Service.DataTransfer.UserIdentity;
import StockX.Kartik.Authentication_Service.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserIdentity>> getCurrentUser() {
        return ResponseEntity.ok(authService.getUsers());
    }

}

