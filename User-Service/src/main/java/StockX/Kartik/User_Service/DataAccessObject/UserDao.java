package StockX.Kartik.User_Service.DataAccessObject;

import StockX.Kartik.User_Service.DataTransferObject.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
