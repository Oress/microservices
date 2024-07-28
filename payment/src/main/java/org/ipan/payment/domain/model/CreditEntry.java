package org.ipan.payment.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_entry")
@Entity
public class CreditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "total_credit_amount", precision = 10, scale = 2)
    private BigDecimal totalCreditAmount;

    public void addCreditAmount(BigDecimal amount) {
        totalCreditAmount = totalCreditAmount.add(amount);
    }

    public void subtractCreditAmount(BigDecimal amount) {
        totalCreditAmount = totalCreditAmount.subtract(amount);
    }
}