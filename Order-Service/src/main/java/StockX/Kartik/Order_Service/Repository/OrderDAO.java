package StockX.Kartik.Order_Service.Repository;

import StockX.Kartik.Order_Service.DataTransfer.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {
}
