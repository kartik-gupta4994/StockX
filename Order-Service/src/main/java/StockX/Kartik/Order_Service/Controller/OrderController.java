package StockX.Kartik.Order_Service.Controller;

import StockX.Kartik.Order_Service.DataTransfer.OrderResponse;
import StockX.Kartik.Order_Service.DataTransfer.PlaceOrderRequest;
import StockX.Kartik.Order_Service.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody @Valid PlaceOrderRequest request,
            @RequestHeader("Authorization") String token)//don't use this parameter and verification logic should always be in securityFilterChain
    {

        //Long userId = JwtUtil.extractUserId(token); // implement or reuse this from user-service
        return ResponseEntity.ok(orderService.placeOrder((long)1, request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getUserOrders(){//Need to implement @AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(orderService.getOrdersForUser((long)1));
    }

}

