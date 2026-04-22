package com.development.account.infraestructure.output.messaging;

import com.development.account.domain.dto.Messaging.CustomerDeletedEventDto;
import com.development.account.domain.dto.Messaging.CustomerManagementEventDto;
import com.development.account.infraestructure.output.repository.CustomerViewRepository;
import com.development.account.infraestructure.output.repository.entity.ClientView;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventListener {

    private final CustomerViewRepository customerViewRepository;

    @RabbitListener(queues = "customer.created.queue")
    public void handleCustomerCreated(CustomerManagementEventDto event) {
        ClientView view = new ClientView(
                event.getClientId(),
                event.getName(),
                event.getStatus()
        );
        customerViewRepository.save(view);
    }

    @RabbitListener(queues = "customer.updated.queue")
    public void handleCustomerUpdated(CustomerManagementEventDto event) {
        customerViewRepository.findById(event.getClientId())
                .ifPresent(customer -> {
                    customer.setName(event.getName());
                    customer.setStatus(event.getStatus());
                    customerViewRepository.save(customer);
                });
    }

    @RabbitListener(queues = "customer.deleted.queue")
    public void handleCustomerDeleted(CustomerDeletedEventDto event) {
        customerViewRepository.findById(event.getClientId())
                .ifPresent(customerViewRepository::delete);
    }

}