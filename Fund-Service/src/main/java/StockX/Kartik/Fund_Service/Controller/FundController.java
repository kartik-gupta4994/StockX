package StockX.Kartik.Fund_Service.Controller;

import StockX.Authorization.UserPrincipal;
import StockX.DataTransfer.FundRequest;
import StockX.DataTransfer.OrderPlaceResponse;
import StockX.Kartik.Fund_Service.Service.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class FundController {

    private final FundService fundService;

    @PostMapping("/add")
    public ResponseEntity<?> addFunds(@AuthenticationPrincipal UserPrincipal user, @RequestBody FundRequest request)
    {
        fundService.addFunds(user.getUserId(), request.getAmount());
        return ResponseEntity.ok("Funds added successfully.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<OrderPlaceResponse> withdrawFunds(@AuthenticationPrincipal UserPrincipal user, @RequestBody FundRequest request)
    {
        OrderPlaceResponse response = fundService.withdrawFunds(user.getUserId(),request.getAmount());
        if(response.isSuccess())
            return ResponseEntity.ok(response);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@AuthenticationPrincipal UserPrincipal user)
    {
        return ResponseEntity.ok(fundService.getBalance(user.getUserId()));
    }
}

