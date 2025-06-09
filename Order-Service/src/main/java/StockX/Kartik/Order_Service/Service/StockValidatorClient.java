package StockX.Kartik.Order_Service.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class StockValidatorClient {

//    private final RestTemplate restTemplate;
//
//    public StockValidatorClient(RestTemplateBuilder builder) {
//        this.restTemplate = builder.build();
//    }

    public boolean isValidStock(String symbol) {
        try {

            //Microservice Integration
            //ResponseEntity<Void> response = restTemplate.getForEntity("http://localhost:8081/api/stocks/validate/" + symbol, Void.class);
            return symbol.isEmpty();//response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}

