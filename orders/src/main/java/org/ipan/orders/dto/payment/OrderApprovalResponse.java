package org.ipan.orders.dto.payment;

import lombok.Data;
import org.ipan.common.domain.OrderApprovalStatus;

import java.time.Instant;
import java.util.List;

@Data
public class OrderApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private Instant createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}
