package StockX.Kartik.Portfolio_Service.Service;

import StockX.Authorization.UserPrincipal;
import StockX.DataTransfer.OrderResponse;
import StockX.DataTransfer.OrderStatus;
import StockX.DataTransfer.OrderType;
import StockX.Kartik.Portfolio_Service.DataTransfer.OrderDTO;
import StockX.Kartik.Portfolio_Service.DataTransfer.PortfolioSummary;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final OrderClient orderClient;

    public List<PortfolioSummary> getPortfolio() {

        List<OrderResponse> orders = orderClient.getUserOrders().getBody();
        assert orders != null;

        Map<String, List<OrderDTO>> groupedByStock = orders.stream()
                .map(this::toResponse)
                .filter(o -> o.getStatus().equals(OrderStatus.COMPLETE))
                .collect(Collectors.groupingBy(OrderDTO::getStockSymbol));

        List<PortfolioSummary> summaries = new ArrayList<>();

        for (var entry : groupedByStock.entrySet()) {
            String symbol = entry.getKey();
            List<OrderDTO> userOrders = entry.getValue();

            int totalQty = 0;
            double totalCost = 0.0;

            for (OrderDTO o : userOrders) {
                int sign = o.getOrderType().equals(OrderType.BUY) ? 1 : -1;
                totalQty += sign * o.getQuantity();
                totalCost += sign * o.getPriceAtOrder() * o.getQuantity();
            }

            if (totalQty != 0) {
                summaries.add(new PortfolioSummary(
                        symbol,
                        totalQty,
                        Math.round(totalCost / totalQty * 100.0) / 100.0
                ));
            }
        }

        return summaries;
    }

    private OrderDTO toResponse(OrderResponse response) {
        return OrderDTO.builder()
                .stockSymbol(response.getStockSymbol())
                .quantity(response.getQuantity())
                .priceAtOrder(response.getPriceAtOrder())
                .orderType(response.getOrderType())
                .status(response.getStatus())
                .build();
    }

}

