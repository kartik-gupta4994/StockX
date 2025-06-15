package StockX.Kartik.Portfolio_Service.Service;

import StockX.Authorization.UserPrincipal;
import StockX.DataTransfer.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE", configuration = FeignClientConfig.class)
public interface OrderClient {

    @GetMapping("api/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders();
}

