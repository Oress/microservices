package org.ipan.common.messaging;

public class Messaging {
    public class Exchanges {
        public static String CUSTOMER_CREATED = "CustomerCreatedExchange";
        public static String ORDER = "order";
    }

    public class RoutingKeys {
        public static String ORDER_CREATED = "created";
    }
}
