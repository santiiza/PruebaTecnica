package com.development.account.application.service;

import com.development.account.application.input.port.AccountInputPort;
import com.development.account.application.output.port.AccountAdapterPort;
import com.development.account.domain.dto.Account.AccountPatchRequestDto;
import com.development.account.domain.dto.Account.AccountRequestDto;
import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.infraestructure.exception.CustomDifferentAccountException;
import com.development.account.infraestructure.exception.CustomNotFoundException;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountInputPort {

    private final AccountAdapterPort accountAdapterPort;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto request) {
        return accountAdapterPort.save(accountMapper.toEntity(request));
    }

    @Override
    public AccountResponseDto getAccountById(String id) {
        Account account = accountAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Account [" + id + "] not found"));
        return accountMapper.toResponseDto(account);
    }

    @Override
    public AccountResponseDto updateAccount(String id, AccountRequestDto request) {
        Account account = accountAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Account: " + id + " not found"));
        if(!account.getAccountNumber().equals(request.getAccountNumber())) {
            throw new CustomDifferentAccountException("The account is different");
        }
        return accountAdapterPort.save(accountMapper.toEntity(request));
    }

    @Override
    public AccountResponseDto updateAccount(String id, AccountPatchRequestDto request) {
        Account account = accountAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Account: " + id + " not found"));
        if(!account.getAccountNumber().equals(request.getAccountNumber())) {
            throw new CustomDifferentAccountException("The account is different");
        }
        if (request.getCustomer() != null) {
            account.setAccountNumber(request.getCustomer());
        }
        if (request.getAccountType() != null) {
            account.setAccountType(request.getAccountType());
        }
        if (request.getInitialBalance() != null) {
            account.setInitialBalance(request.getInitialBalance());
        }
        if (request.getStatus() != null) {
            account.setStatus(request.getStatus());
        }
        return accountAdapterPort.save(account);
    }

    @Override
    public void deleteAccount(String id) {
        Account account = accountAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Account [" + id + "] not found"));
        accountAdapterPort.delete(account);
    }
}
