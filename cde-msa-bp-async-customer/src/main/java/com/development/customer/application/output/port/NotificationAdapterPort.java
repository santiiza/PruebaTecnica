package com.development.customer.application.output.port;

import com.development.customer.domain.dto.messaging.CustomerDeletedEventDto;
import com.development.customer.domain.dto.messaging.CustomerManagementEventDto;

public interface NotificationAdapterPort {
    void publishCustomerCreated(CustomerManagementEventDto event);
    void publishCustomerUpdated(CustomerManagementEventDto event);
    void publishCustomerDeleted(CustomerDeletedEventDto event);
}
