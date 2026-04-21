package com.development.customer.infraestructure.output.messaging;

import com.development.customer.domain.dto.messaging.CustomerCreatedEventDto;
import com.development.customer.domain.dto.messaging.CustomerDeletedEventDto;
import com.development.customer.domain.dto.messaging.CustomerUpdatedEventDto;
import com.development.customer.infraestructure.input.adapter.rest.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventPublisher {

    private final AmqpTemplate amqpTemplate;

    public void publishCustomerCreated(CustomerCreatedEventDto customer) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                customer
        );
    }

    public void publishCustomerUpdated(CustomerUpdatedEventDto customer) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                customer
        );
    }

    public void publishCustomerDeleted(CustomerDeletedEventDto customer) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                customer
        );
    }
}