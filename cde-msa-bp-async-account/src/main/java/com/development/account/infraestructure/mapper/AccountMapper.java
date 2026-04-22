package com.development.account.infraestructure.mapper;

import com.development.account.domain.dto.Account.AccountRequestDto;
import com.development.account.domain.dto.Account.AccountResponseDto;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import com.development.account.infraestructure.output.repository.entity.Account;
import com.development.account.infraestructure.output.repository.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto toResponseDto(Account entity) {
        return AccountResponseDto.builder()
                .accountNumber(entity.getAccountNumber())
                .clientId(entity.getClientId())
                .accountType(entity.getAccountType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .build();
    }

    public Account toEntity(AccountRequestDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setClientId(dto.getClientId());
        account.setAccountType(dto.getAccountType());
        account.setInitialBalance(dto.getInitialBalance());
        account.setStatus(dto.getStatus());
        return account;
    }

    public TransactionResponseDto toResponseDto(Transaction entity) {
        return TransactionResponseDto.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccount().getAccountNumber())
                .transactionDate(entity.getTransactionDate())
                .transactionType(entity.getTransactionType())
                .amount(entity.getAmount())
                .balance(entity.getBalance())
                .build();
    }
}
