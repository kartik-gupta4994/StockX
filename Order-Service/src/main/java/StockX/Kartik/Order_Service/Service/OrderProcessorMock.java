package StockX.Kartik.Order_Service.Service;

import StockX.Kartik.Order_Service.DataTransfer.Order;
import StockX.Kartik.Order_Service.Repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import StockX.DataTransfer.OrderStatus;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class OrderProcessorMock {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderWebSocketService webSocketService;

    private final Executor executor = Executors.newCachedThreadPool();
    private final Random random = new Random();

    public void processOrderAsync(Order order) {
        executor.execute(() -> {
            try {
                Thread.sleep(5000); // simulate 5-second processing delay

                order.setStatus(random.nextBoolean() ? OrderStatus.COMPLETE : OrderStatus.CANCELLED);
                orderDAO.save(order);

                webSocketService.sendOrderStatusUpdate(order);
                System.out.println("Order " + order.getId() + " status updated to " + order.getStatus());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
