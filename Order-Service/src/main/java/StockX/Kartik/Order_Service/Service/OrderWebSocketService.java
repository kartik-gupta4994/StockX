package StockX.Kartik.Order_Service.Service;

import StockX.Kartik.Order_Service.DataTransfer.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderWebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendOrderStatusUpdate(Order order) {
        messagingTemplate.convertAndSend(
                "/topic/order-status/" + order.getUserId(),
                Map.of("orderId", order.getId(), "status", order.getStatus().name())
        );
    }
}

