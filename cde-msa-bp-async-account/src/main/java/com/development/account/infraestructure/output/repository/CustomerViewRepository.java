package com.development.account.infraestructure.output.repository;

import com.development.account.infraestructure.output.repository.entity.ClientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerViewRepository extends JpaRepository<ClientView, String> {
}
