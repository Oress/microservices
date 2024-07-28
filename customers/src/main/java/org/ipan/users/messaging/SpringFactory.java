package org.ipan.users.messaging;

import org.ipan.common.messaging.Messaging;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFactory {
    @Bean
    public Exchange customerCreatedExchange() {
        return new FanoutExchange(Messaging.Exchanges.CUSTOMER_CREATED);
    }
}
