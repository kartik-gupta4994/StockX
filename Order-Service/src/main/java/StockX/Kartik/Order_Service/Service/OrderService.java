package StockX.Kartik.Order_Service.Service;

import StockX.DataTransfer.FundRequest;
import StockX.Kartik.Order_Service.DataTransfer.Order;
import StockX.DataTransfer.OrderResponse;
import StockX.DataTransfer.OrderStatus;
import StockX.Kartik.Order_Service.DataTransfer.PlaceOrderRequest;
import StockX.Kartik.Order_Service.Repository.OrderDAO;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;
    private final FundsClient fundsClient;
    private final OrderProcessorMock orderProcessor;
    @Transactional
    public OrderResponse placeOrder(Long userId, PlaceOrderRequest request){

        double orderAmount = request.getPrice() * request.getQuantity();
        FundRequest fundRequest = new FundRequest(orderAmount);

        Order order = new Order();
        order.setUserId(userId);
        order.setStockSymbol(request.getStockSymbol());
        order.setType(request.getType());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());

        try {
            fundsClient.withdrawFunds(fundRequest);
        }
        catch (FeignException.BadRequest e) {
            order.setStatus(OrderStatus.CANCELLED);
            return toResponse(order);
        }

        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order saved = orderDAO.save(order);
        orderProcessor.processOrderAsync(saved);
        return toResponse(saved);
    }

    public List<OrderResponse> getOrdersForUser(Long userId) {
        List<Order> orders = orderDAO.findByUserId(userId);
        return orders.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //don't want to expose the Order Entity with response
    //Use @Builder in OrderResponse by Lombok
    private OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .stockSymbol(order.getStockSymbol())
                .quantity(order.getQuantity())
                .priceAtOrder(order.getPrice())
                .orderType(order.getType())
                .status(order.getStatus())
                .message(order.getStatus() == OrderStatus.CANCELLED ? "Insufficient Balance" : " ")
                .build();
    }
}

