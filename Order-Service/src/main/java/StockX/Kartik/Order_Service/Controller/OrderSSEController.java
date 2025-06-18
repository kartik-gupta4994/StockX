package StockX.Kartik.Order_Service.Controller;


import StockX.Kartik.Order_Service.Service.OrderStatusNotifier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/orders/stream")
@RequiredArgsConstructor
public class OrderSSEController {

    private final OrderStatusNotifier notifier;

    @GetMapping(value = "/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamOrderStatus(@PathVariable("userId") Long userId) {
        return notifier.addUser(userId);
    }
}

