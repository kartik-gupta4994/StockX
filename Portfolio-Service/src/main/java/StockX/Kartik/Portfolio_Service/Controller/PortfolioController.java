package StockX.Kartik.Portfolio_Service.Controller;

import StockX.Kartik.Portfolio_Service.DataTransfer.PortfolioSummary;
import StockX.Kartik.Portfolio_Service.Service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<List<PortfolioSummary>> getPortfolio(
            //@AuthenticationPrincipal UserPrincipal user,
            @RequestHeader("Authorization") String authHeader
    )
    {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(portfolioService.getPortfolio(token));
    }
}

