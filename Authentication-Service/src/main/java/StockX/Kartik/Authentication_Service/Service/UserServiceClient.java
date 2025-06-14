package StockX.Kartik.Authentication_Service.Service;

import StockX.Kartik.Authentication_Service.DataTransfer.UserProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @PostMapping("api/user/internal/create-profile")
    void createProfile(@RequestBody UserProfileRequest request);
}
