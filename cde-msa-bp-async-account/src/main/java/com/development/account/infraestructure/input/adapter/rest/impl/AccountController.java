package com.development.account.infraestructure.input.adapter.rest.impl;

import com.development.account.application.input.port.AccountInputPort;
import com.development.account.domain.dto.Account.AccountPatchRequestDto;
import com.development.account.domain.dto.Account.AccountRequestDto;
import com.development.account.domain.dto.Account.AccountResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountInputPort accountInputPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount(@Valid @RequestBody AccountRequestDto request) {
        return accountInputPort.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponseDto getAccountById(@PathVariable String id) {
        return accountInputPort.getAccountById(id);
    }

    @PutMapping("/{id}")
    public AccountResponseDto updateAccount(
            @PathVariable String id,
            @Valid @RequestBody AccountRequestDto request) {
        return accountInputPort.updateAccount(id, request);
    }

    @PatchMapping("/{id}")
    public AccountResponseDto updateCustomerPartially(
            @PathVariable String id,
            @Valid @RequestBody AccountPatchRequestDto request) {
        return accountInputPort.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String id) {
        accountInputPort.deleteAccount(id);
    }

}
