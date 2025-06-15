package StockX.Kartik.Fund_Service.DataTransfer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fund_transactions")
@Data
@AllArgsConstructor
public class FundTransaction {

    @Id
    @GeneratedValue
    private UUID id;

    private long userId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDateTime createdAt = LocalDateTime.now();
}

