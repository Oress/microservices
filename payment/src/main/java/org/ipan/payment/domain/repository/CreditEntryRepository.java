package org.ipan.payment.domain.repository;


import org.ipan.payment.domain.model.CreditEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditEntryRepository extends JpaRepository<CreditEntry, UUID> {
    Optional<CreditEntry> findByCustomerId(UUID customerId);
}
