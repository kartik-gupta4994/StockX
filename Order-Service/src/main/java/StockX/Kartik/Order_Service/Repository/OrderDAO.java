package StockX.Kartik.Order_Service.Repository;

import StockX.DataTransfer.OrderStatus;
import StockX.Kartik.Order_Service.DataTransfer.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer> {

    List<Order> findByUserId(long userId);

    List<Order> findByStatus(OrderStatus orderStatus);
}
