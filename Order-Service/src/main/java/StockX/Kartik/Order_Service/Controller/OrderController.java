package StockX.Kartik.Order_Service.Controller;

import StockX.Kartik.Order_Service.DataTransfer.OrderResponse;
import StockX.Kartik.Order_Service.DataTransfer.PlaceOrderRequest;
import StockX.Kartik.Order_Service.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody @Valid PlaceOrderRequest request,
            @RequestHeader("Authorization") String token)//dont use this parameter and verification logic should always be in securityFilterChain
    {

        //Long userId = JwtUtil.extractUserId(token); // implement or reuse this from user-service
        return ResponseEntity.ok(orderService.placeOrder((long)1, request));
    }
}

