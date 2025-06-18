package StockX.Kartik.Order_Service.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderStatusNotifier {

    private final Map<Long, SseEmitter> userEmitters = new ConcurrentHashMap<>();

    public SseEmitter addUser(Long userId) {
        SseEmitter emitter = new SseEmitter(10 * 60 * 1000L); // 10 minutes timeout
        userEmitters.put(userId, emitter);

        emitter.onCompletion(() -> userEmitters.remove(userId));
        emitter.onTimeout(() -> userEmitters.remove(userId));
        emitter.onError((e) -> userEmitters.remove(userId));

        return emitter;
    }

    public void sendOrderStatusUpdate(Long userId, String message) {
        SseEmitter emitter = userEmitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("order-status")
                        .data(message)
                        .id(UUID.randomUUID().toString())
                        .reconnectTime(3000));
            } catch (IOException e) {
                userEmitters.remove(userId);
            }
        }
    }
}

