package com.development.account.infraestructure.input.adapter.rest.impl;

import com.development.account.application.input.port.TransactionInputPort;
import com.development.account.domain.dto.Transaction.TransactionRequestDto;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionInputPort transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto createTransaction(@Valid @RequestBody TransactionRequestDto request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public TransactionResponseDto updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequestDto request) {
        return transactionService.updateTransaction(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

}
