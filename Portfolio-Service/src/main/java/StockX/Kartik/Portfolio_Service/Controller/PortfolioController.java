package StockX.Kartik.Portfolio_Service.Controller;

import StockX.Authorization.UserPrincipal;
import StockX.Kartik.Portfolio_Service.DataTransfer.PortfolioSummary;
import StockX.Kartik.Portfolio_Service.Service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<PortfolioSummary>> getPortfolio(@AuthenticationPrincipal UserPrincipal user)
    {
        return ResponseEntity.ok(portfolioService.getPortfolio());
    }
}

