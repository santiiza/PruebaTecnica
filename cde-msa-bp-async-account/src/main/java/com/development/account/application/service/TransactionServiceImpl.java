package com.development.account.application.service;

import com.development.account.application.input.port.TransactionInputPort;
import com.development.account.application.output.port.AccountAdapterPort;
import com.development.account.application.output.port.TransactionAdapterPort;
import com.development.account.domain.dto.Transaction.TransactionRequestDto;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import com.development.account.infraestructure.exception.CustomBalanceUnavailableException;
import com.development.account.infraestructure.exception.CustomDifferentAccountException;
import com.development.account.infraestructure.exception.CustomInactiveException;
import com.development.account.infraestructure.exception.CustomNotFoundException;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.CustomerViewRepository;
import com.development.account.infraestructure.output.repository.entity.Account;
import com.development.account.infraestructure.output.repository.entity.ClientView;
import com.development.account.infraestructure.output.repository.entity.Transaction;
import com.development.account.infraestructure.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionInputPort {

    private final AccountAdapterPort accountAdapterPort;
    private final TransactionAdapterPort transactionAdapterPort;
    private final CustomerViewRepository customerViewRepository;
    private final AccountMapper accountMapper;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto request) {
        Account account = getActiveAccount(request.getAccountNumber());
        validateCustomer(account.getClientId());
        BigDecimal finalBalance = calculateNewBalance(account.getInitialBalance(), request.getAmount());
        validateBalance(finalBalance);
        updateAccountBalance(account, finalBalance);
        Transaction transaction = buildTransaction(account, request.getAmount(), finalBalance);
        return transactionAdapterPort.save(transaction);
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Transaction [" + id + "] not found"));
        return accountMapper.toResponseDto(transaction);
    }

    @Override
    public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto request) {
        Transaction transaction = transactionAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Transaction [" + id + "] not found"));

        //Realizar la actualización del saldo de la transaccion original
        Account accountOld = getActiveAccount(transaction.getAccount().getAccountNumber());
        validateCustomer(accountOld.getClientId());
        if(!accountOld.getAccountNumber().equals(request.getAccountNumber())) {
            throw new CustomDifferentAccountException("The account is different");
        }
        BigDecimal finalBalanceOld = calculateNewBalance(accountOld.getInitialBalance(), transaction.getAmount().negate());
        validateBalance(finalBalanceOld);
        updateAccountBalance(accountOld, finalBalanceOld);

        //Actualizo el nuevo saldo a la cuenta
        Account accountNew = getActiveAccount(transaction.getAccount().getAccountNumber());
        BigDecimal finalBalanceNew = calculateNewBalance(accountNew.getInitialBalance(), request.getAmount());
        validateBalance(finalBalanceNew);
        updateAccountBalance(accountNew, finalBalanceNew);

        //Actualizar los valores de la transacción
        transaction.setAccount(accountNew);
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setBalance(finalBalanceNew);
        transaction.setTransactionType(request.getAmount().signum() >= 0
                ? String.format(Constants.DEPOSITO, request.getAmount())
                : String.format(Constants.RETIRO, request.getAmount()));

        return transactionAdapterPort.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionAdapterPort.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Transaction [" + id + "] not found"));
        Account account = getActiveAccount(transaction.getAccount().getAccountNumber());
        validateCustomer(account.getClientId());
        BigDecimal finalBalance = calculateNewBalance(account.getInitialBalance(), transaction.getAmount().negate());
        validateBalance(finalBalance);
        updateAccountBalance(account, finalBalance);
        transactionAdapterPort.delete(transaction);
    }

    private Account getActiveAccount(String accountNumber) {
        Account account = accountAdapterPort.findById(accountNumber)
                .orElseThrow(() -> new CustomNotFoundException("Account: " + accountNumber + " not found"));
        if (!account.getStatus()) {
            throw new CustomInactiveException("The account is inactive");
        }
        return account;
    }

    private BigDecimal calculateNewBalance(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.add(amount);
    }

    private void validateBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomBalanceUnavailableException("Balance not available");
        }
    }

    private void updateAccountBalance(Account account, BigDecimal newBalance) {
        account.setInitialBalance(newBalance);
        accountAdapterPort.save(account);
    }

    private Transaction buildTransaction(Account account, BigDecimal amount, BigDecimal finalBalance) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setBalance(finalBalance);
        transaction.setTransactionType(amount.signum() >= 0
                ? String.format(Constants.DEPOSITO, amount)
                : String.format(Constants.RETIRO, amount));
        return transaction;
    }

    private void validateCustomer(String clientId) {
        ClientView customer = customerViewRepository.findById(clientId)
                .orElseThrow(() -> new CustomNotFoundException("Client [" + clientId + "] not found"));
        if (!customer.getStatus()) {
            throw new CustomInactiveException("Client is inactive");
        }
    }
}
