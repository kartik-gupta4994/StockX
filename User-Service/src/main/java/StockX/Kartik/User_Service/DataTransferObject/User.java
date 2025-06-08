package StockX.Kartik.User_Service.DataTransferObject;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private BigDecimal walletBalance = BigDecimal.ZERO;
    private final Timestamp createdAt = Timestamp.from(Instant.now());
}
