package com.rezende.bank_account.account.service;

import com.rezende.bank_account.account.dto.CreateAccountRequest;
import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateAccount {

    private final AccountRepository accountRepository;

    public CreateAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void create(CreateAccountRequest request) {
        validateRequest(request);

        Account account = new Account(request.accountNumber(), request.balance());

        accountRepository.save(account);
    }

    private void validateRequest(CreateAccountRequest request) {
        if (Objects.isNull(request) || request.accountNumber() <= 0) {
            throw new IllegalArgumentException("Account number greater than 0 is required!");
        }

        if (request.balance() <= 0) {
            throw new IllegalArgumentException("Balance greater than 0 is required!");
        }
    }
}
