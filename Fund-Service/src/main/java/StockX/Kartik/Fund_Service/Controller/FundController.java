package StockX.Kartik.Fund_Service.Controller;

import StockX.Authorization.UserPrincipal;
import StockX.Kartik.Fund_Service.Service.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class FundController {

    private final FundService fundService;

    @PostMapping("/add")
    public ResponseEntity<?> addFunds(@AuthenticationPrincipal UserPrincipal user, @RequestParam("amount") double amount)
    {
        fundService.addFunds(user.getUserId(), amount);
        return ResponseEntity.ok("Funds added successfully.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFunds(@AuthenticationPrincipal UserPrincipal user, @RequestParam("amount") double amount)
    {
        fundService.withdrawFunds(user.getUserId(),amount);
        return ResponseEntity.ok("Funds withdrawn successfully.");
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@AuthenticationPrincipal UserPrincipal user)
    {
        return ResponseEntity.ok(fundService.getBalance(user.getUserId()));
    }
}

