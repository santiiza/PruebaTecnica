package com.development.customer.infraestructure.output.repository;

import com.development.customer.infraestructure.output.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentification(String identification);
    Optional<Customer> findByClId(String clId);
    boolean existsByIdentification(String identificacion);

}
