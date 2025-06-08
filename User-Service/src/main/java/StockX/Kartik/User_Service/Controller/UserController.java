package StockX.Kartik.User_Service.Controller;
import StockX.Kartik.User_Service.DataTransferObject.AuthResponse;
import StockX.Kartik.User_Service.DataTransferObject.LoginRequest;
import StockX.Kartik.User_Service.DataTransferObject.RegisterRequest;
import StockX.Kartik.User_Service.DataTransferObject.User;
import StockX.Kartik.User_Service.Service.JwtUtil;
import StockX.Kartik.User_Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// === UserController ===
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getCurrentUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
