package com.development.account.application.input.port;

import com.development.account.domain.dto.Account.AccountPatchRequestDto;
import com.development.account.domain.dto.Account.AccountRequestDto;
import com.development.account.domain.dto.Account.AccountResponseDto;

public interface AccountInputPort {
    AccountResponseDto createAccount(AccountRequestDto request);
    AccountResponseDto getAccountById(String id);
    AccountResponseDto updateAccount(String id, AccountRequestDto request);
    AccountResponseDto updateAccount(String id, AccountPatchRequestDto request);
    void deleteAccount(String id);
}
