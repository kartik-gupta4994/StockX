package StockX.Kartik.Portfolio_Service.Service;

import StockX.Kartik.Portfolio_Service.DataTransfer.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClient {

    private final RestTemplate restTemplate;

    //@Value("${order.service.url}")
    private String orderServiceUrl;

    public List<OrderDTO> fetchUserOrders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<OrderDTO[]> response = restTemplate.exchange(
                orderServiceUrl + "/api/orders",
                HttpMethod.GET,
                entity,
                OrderDTO[].class
        );

        assert response.getBody() != null;
        return Arrays.asList(response.getBody());
    }
}

