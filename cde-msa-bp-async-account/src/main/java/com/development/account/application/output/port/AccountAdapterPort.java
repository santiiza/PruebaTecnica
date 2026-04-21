package com.development.account.application.output.port;

import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.infraestructure.output.repository.entity.Account;

import java.util.Optional;

public interface AccountAdapterPort {
    AccountResponseDto save(Account acount);
    Optional<Account> findById(String id);
    void delete(Account acount);
}
