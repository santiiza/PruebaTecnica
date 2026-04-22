package com.development.customer.infraestructure.input.adapter.rest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "customer.exchange";

    public static final String ROUTING_KEY_CREATED = "customer.created";
    public static final String ROUTING_KEY_UPDATED = "customer.updated";
    public static final String ROUTING_KEY_DELETED = "customer.deleted";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue createdQueue() {
        return new Queue("customer.created.queue", true);
    }

    @Bean
    public Queue updatedQueue() {
        return new Queue("customer.updated.queue", true);
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue("customer.deleted.queue", true);
    }

    @Bean
    public Binding bindingCreated(Queue createdQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createdQueue).to(exchange).with(ROUTING_KEY_CREATED);
    }

    @Bean
    public Binding bindingUpdated(Queue updatedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updatedQueue).to(exchange).with(ROUTING_KEY_UPDATED);
    }

    @Bean
    public Binding bindingDeleted(Queue deletedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deletedQueue).to(exchange).with(ROUTING_KEY_DELETED);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
