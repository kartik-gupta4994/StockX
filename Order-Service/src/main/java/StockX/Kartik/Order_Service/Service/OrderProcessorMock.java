package StockX.Kartik.Order_Service.Service;

import StockX.Kartik.Order_Service.DataTransfer.Order;
import StockX.Kartik.Order_Service.Repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import StockX.DataTransfer.OrderStatus;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class OrderProcessorMock {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderStatusNotifier statusNotifier;

    @Scheduled(fixedRate = 10000)
    public void processPendingOrders() {
        List<Order> pendingOrders = orderDAO.findByStatus(OrderStatus.PENDING);

        for (Order order : pendingOrders) {
            order.setStatus(OrderStatus.COMPLETE);
            orderDAO.save(order);

            statusNotifier.sendOrderStatusUpdate(order.getUserId(),
                    "Order " + order.getStockSymbol() + " is now COMPLETED");
        }
    }
}
