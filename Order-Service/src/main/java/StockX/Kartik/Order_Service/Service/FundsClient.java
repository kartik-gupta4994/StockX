package StockX.Kartik.Order_Service.Service;

import StockX.DataTransfer.FundRequest;
import StockX.DataTransfer.OrderPlaceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FUND-SERVICE", configuration = FeignClientConfig.class)
public interface FundsClient {

    @PostMapping(value = "api/funds/withdraw")
    public ResponseEntity<OrderPlaceResponse> withdrawFunds(@RequestBody FundRequest amount);
}

