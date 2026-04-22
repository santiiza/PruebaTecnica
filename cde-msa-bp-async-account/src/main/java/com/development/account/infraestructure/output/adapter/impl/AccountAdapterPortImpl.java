package com.development.account.infraestructure.output.adapter.impl;

import com.development.account.application.output.port.AccountAdapterPort;
import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.AccountRepository;
import com.development.account.infraestructure.output.repository.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountAdapterPortImpl implements AccountAdapterPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto save(Account account) {
        return accountMapper.toResponseDto(
                accountRepository.save(account));
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findByClientId(String clientId) {
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }

    @Override
    public void delete(Account account){
        accountRepository.delete(account);
    }

}
