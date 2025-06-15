package StockX.Kartik.User_Service.Controller;

import StockX.Authorization.UserPrincipal;
import StockX.Kartik.User_Service.DataTransferObject.UserProfileRequest;
import StockX.Kartik.User_Service.DataTransferObject.UserProfileResponse;
import StockX.Kartik.User_Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


// === UserController ===
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(userService.getProfile(userPrincipal.getUsername()));
    }

    //Will be called by authentication client via Feign
    @PostMapping("/internal/create-profile")
    public ResponseEntity<?> createProfile(@RequestBody UserProfileRequest request) {

        userService.createProfile(request);
        return ResponseEntity.ok().build();
    }

}
