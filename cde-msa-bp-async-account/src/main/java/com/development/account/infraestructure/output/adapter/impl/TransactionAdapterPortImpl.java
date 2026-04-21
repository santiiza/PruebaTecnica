package com.development.account.infraestructure.output.adapter.impl;

import com.development.account.application.output.port.TransactionAdapterPort;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.TransactionRepository;
import com.development.account.infraestructure.output.repository.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionAdapterPortImpl implements TransactionAdapterPort {

    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    @Override
    public TransactionResponseDto save(Transaction transaction) {
        return accountMapper.toResponseDto(
                transactionRepository.save(transaction));
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void delete(Transaction account){
        transactionRepository.delete(account);
    }

}
