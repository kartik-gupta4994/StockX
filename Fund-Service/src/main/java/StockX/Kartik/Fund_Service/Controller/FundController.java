package StockX.Kartik.Fund_Service.Controller;

import StockX.Kartik.Fund_Service.Service.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class FundController {

    private final FundService fundService;

    @PostMapping("/add")
    public ResponseEntity<?> addFunds(@RequestParam double amount)//(@AuthenticationPrincipal UserPrincipal user, @RequestParam double amount)
    {
        fundService.addFunds("1",amount);//user.getUserId(), amount);
        return ResponseEntity.ok("Funds added successfully.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFunds(@RequestParam double amount)//(@AuthenticationPrincipal UserPrincipal user, @RequestParam double amount)
    {
        fundService.withdrawFunds("1",amount);//user.getUserId(), amount);
        return ResponseEntity.ok("Funds withdrawn successfully.");
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance()//(@AuthenticationPrincipal UserPrincipal user)
    {
        return ResponseEntity.ok(fundService.getBalance("1"));
    }
}

