package org.ipan.payment.domain.repository;


import org.ipan.payment.domain.model.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, UUID> {
    Optional<List<CreditHistory>> findByCustomerId(UUID customerId);
}
