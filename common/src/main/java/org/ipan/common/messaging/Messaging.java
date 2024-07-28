package org.ipan.common.messaging;

public class Messaging {
    public class Exchanges {
        public static final String CUSTOMER_CREATED = "customer";
        public static final String ORDER = "order";
        public static final String PAYMENT = "payment";
    }

    public class Queues {
        public static final String PAYMENT = "payment";
        public static final String ORDERS = "orders";
    }

    public class RoutingKeys {
        public static final String ORDER_CREATED = "created";
        public static final String PAYMENT_COMPLETED = "completed";
    }
}
