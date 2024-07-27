package org.ipan.orders.domain.repository;

import jakarta.validation.constraints.NotNull;
import org.ipan.orders.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByTrackingId(String trackingId);
}
