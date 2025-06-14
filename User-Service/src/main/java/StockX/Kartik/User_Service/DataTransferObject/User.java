package StockX.Kartik.User_Service.DataTransferObject;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "user_profiles")
public class User {

    @Id
    private String username;
    private String name;
    private String email;
    private BigDecimal walletBalance = BigDecimal.ZERO;
    private final Timestamp createdAt = Timestamp.from(Instant.now());
}
