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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;

    @Transactional
    public OrderResponse placeOrder(Long userId, PlaceOrderRequest request) {

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

    public List<OrderResponse> getOrdersForUser(Long userId) {
        List<Order> orders = orderDAO.findByUserId(userId);
        return orders.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //don't want to expose the Order Entity with response
    //copyProperties is a utility method from Spring that copies matching fields by name/type from one object to another.
    private OrderResponse toResponse(Order order) {
        OrderResponse res = new OrderResponse();
        BeanUtils.copyProperties(order, res);
        return res;
    }
}

