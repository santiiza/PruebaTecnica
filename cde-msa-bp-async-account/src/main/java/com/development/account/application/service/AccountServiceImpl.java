package com.development.account.application.service;

import com.development.account.application.input.port.AccountInputPort;
import com.development.account.application.output.port.AccountAdapterPort;
import com.development.account.domain.dto.Account.AccountPatchRequestDto;
import com.development.account.domain.dto.Account.AccountRequestDto;
import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.infraestructure.exception.CustomDifferentAccountException;
import com.development.account.infraestructure.exception.CustomInactiveException;
import com.development.account.infraestructure.exception.CustomNotFoundException;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.CustomerViewRepository;
import com.development.account.infraestructure.output.repository.entity.Account;
import com.development.account.infraestructure.output.repository.entity.ClientView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountInputPort {

    private final AccountAdapterPort accountAdapterPort;
    private final CustomerViewRepository customerViewRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto request) {
        if (accountAdapterPort.existsById(request.getAccountNumber())) {
            throw new IllegalArgumentException("The account already exists.");
        }
        validateCustomer(request.getClientId());
        return accountAdapterPort.save(accountMapper.toEntity(request));
    }

    @Override
    public AccountResponseDto getAccountById(String id) {
        Account account = findAccountById(id);
        return accountMapper.toResponseDto(account);
    }

    @Override
    public AccountResponseDto updateAccount(String id, AccountRequestDto request) {
        Account account = findAccountById(id);
        validateCustomer(account.getClientId());
        validateSameAccount(account.getAccountNumber(), request.getAccountNumber());
        return accountAdapterPort.save(accountMapper.toEntity(request));
    }

    @Override
    public AccountResponseDto updateAccount(String id, AccountPatchRequestDto request) {
        Account account = findAccountById(id);
        validateCustomer(account.getClientId());
        validateSameAccount(account.getAccountNumber(), request.getAccountNumber());

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
        Account account = findAccountById(id);
        accountAdapterPort.delete(account);
    }

    private void validateCustomer(String customerId) {
        ClientView customer = customerViewRepository.findById(customerId)
                .orElseThrow(() -> new CustomNotFoundException("Customer [" + customerId + "] not found"));
        if (!customer.getStatus()) {
            throw new CustomInactiveException("Customer is inactive");
        }
    }

    private Account findAccountById(String id) {
        return accountAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Account [" + id + "] not found"));
    }

    private void validateSameAccount(String existingAccountNumber, String requestAccountNumber) {
        if (requestAccountNumber != null && !existingAccountNumber.equals(requestAccountNumber)) {
            throw new CustomDifferentAccountException("The account is different");
        }
    }
}
