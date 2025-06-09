package StockX.Kartik.Order_Service.Service;

import StockX.Kartik.Order_Service.DataTransfer.Order;
import StockX.Kartik.Order_Service.DataTransfer.OrderResponse;
import StockX.Kartik.Order_Service.DataTransfer.OrderStatus;
import StockX.Kartik.Order_Service.DataTransfer.PlaceOrderRequest;
import StockX.Kartik.Order_Service.Repository.OrderDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;
    private final StockValidatorClient stockValidatorClient;

    @Transactional
    public OrderResponse placeOrder(Long userId, PlaceOrderRequest request) {
        // Validate stock
        if (!stockValidatorClient.isValidStock(request.getStockSymbol())) {
            throw new IllegalArgumentException("Invalid stock symbol");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setStockSymbol(request.getStockSymbol());
        order.setType(request.getType());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order saved = orderDAO.save(order);
        return toResponse(saved);
    }

    //dont want to expose the Order Entity with response
    private OrderResponse toResponse(Order order) {
        OrderResponse res = new OrderResponse();
        BeanUtils.copyProperties(order, res);
        return res;
    }
}

