package com.development.customer.infraestructure.output.messaging;

import com.development.customer.domain.dto.messaging.CustomerManagementEventDto;
import com.development.customer.domain.dto.messaging.CustomerDeletedEventDto;
import com.development.customer.infraestructure.input.adapter.rest.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventPublisher {

    private final AmqpTemplate amqpTemplate;

    public void publishCustomerCreated(CustomerManagementEventDto event) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_CREATED,
                event
        );
    }

    public void publishCustomerUpdated(CustomerManagementEventDto event) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_UPDATED,
                event
        );
    }

    public void publishCustomerDeleted(CustomerDeletedEventDto event) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_DELETED,
                event
        );
    }
}