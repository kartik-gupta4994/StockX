package StockX.Kartik.Authentication_Service.Repository;

import StockX.Kartik.Authentication_Service.DataTransfer.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepo extends JpaRepository<UserIdentity, Long> {
    Optional<UserIdentity> findByUsername(String username);
    boolean existsByUsername(String username);
}
