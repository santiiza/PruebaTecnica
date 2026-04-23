package com.development.account.infraestructure.input.adapter.rest.impl;

import com.development.account.application.input.port.TransactionInputPort;
import com.development.account.domain.dto.Transaction.TransactionRequestDto;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionInputPort transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto createTransaction(@Valid @RequestBody TransactionRequestDto request) {
        log.info("|-> Started createTransaction");
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable Long id) {
        log.info("|-> Started getTransactionById id: {}", id);
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        log.info("|-> Started deleteTransaction id: {}", id);
        transactionService.deleteTransaction(id);
    }

}
