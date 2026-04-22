package com.development.account.application.output.port;

import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.infraestructure.output.repository.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountAdapterPort {
    AccountResponseDto save(Account account);
    Optional<Account> findById(String id);
    List<Account> findByClientId(String clientId);
    boolean existsById(String id);
    void delete(Account account);
}
