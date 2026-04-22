package com.development.account.application.service;

import com.development.account.application.input.port.ReportInputPort;
import com.development.account.application.output.port.AccountAdapterPort;
import com.development.account.application.output.port.TransactionAdapterPort;
import com.development.account.domain.dto.Report.*;
import com.development.account.infraestructure.exception.CustomInactiveException;
import com.development.account.infraestructure.exception.CustomNotFoundException;
import com.development.account.infraestructure.mapper.AccountMapper;
import com.development.account.infraestructure.output.repository.CustomerViewRepository;
import com.development.account.infraestructure.output.repository.entity.Account;
import com.development.account.infraestructure.output.repository.entity.ClientView;
import com.development.account.infraestructure.output.repository.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportInputPort {

    private final AccountAdapterPort accountAdapterPort;
    private final TransactionAdapterPort transactionAdapterPort;
    private final CustomerViewRepository customerViewRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountStatementDto generateAccountStatement(String clientId, LocalDate startDate, LocalDate endDate) {
        ClientView client = customerViewRepository.findById(clientId)
                .orElseThrow(() -> new CustomNotFoundException("Client [" + clientId + "] not found"));
        if (!client.getStatus()) {
            throw new CustomInactiveException("Client is inactive");
        }

        List<Account> accountList = accountAdapterPort.findByClientId(clientId);

        List<AccountReportDto> accountReportList = accountList.stream()
                .map(account -> buildReportAccount(account, startDate, endDate))
                .toList();

        return AccountStatementDto.builder()
                .client(ClientDto.builder()
                        .clientId(client.getClientId())
                        .name(client.getName())
                        .build())
                .dateRange(DateRangeDto.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .accounts(accountReportList)
                .build();
    }

    private AccountReportDto buildReportAccount(Account account, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List<Transaction> transactionList = transactionAdapterPort.findByAccountNumberAndTransactionDateBetween(
                account.getAccountNumber(),
                startDateTime,
                endDateTime
        );

        List<TransactionReportDto> transactionReportList = transactionList.stream()
                .map(transaction -> TransactionReportDto.builder()
                        .transactionDate(transaction.getTransactionDate())
                        .transactionType(transaction.getTransactionType())
                        .amount(transaction.getAmount())
                        .balance(transaction.getBalance())
                        .build()
                ).toList();

        return AccountReportDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getInitialBalance())
                .transactions(transactionReportList)
                .build();
    }
}
