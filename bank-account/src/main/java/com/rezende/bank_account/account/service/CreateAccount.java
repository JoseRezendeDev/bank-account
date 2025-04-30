package com.rezende.bank_account.account.service;

import com.rezende.bank_account.account.dto.AccountDTO;
import com.rezende.bank_account.account.dto.CreateAccountRequest;
import com.rezende.bank_account.account.exception.AccountAlreadyExistsException;
import com.rezende.bank_account.account.exception.AccountNotFoundException;
import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateAccount {

    private final AccountRepository accountRepository;
    private final GetAccount getAccount;

    public CreateAccount(AccountRepository accountRepository, GetAccount getAccount) {
        this.accountRepository = accountRepository;
        this.getAccount = getAccount;
    }

    public AccountDTO create(CreateAccountRequest request) {
        validateRequest(request);

        Account account = new Account(request.accountNumber(), request.balance());

        Account createdAccount = accountRepository.save(account);

        return entityToDTO(createdAccount);
    }

    public AccountDTO update(Account account) {
        Account updatedAccount = accountRepository.save(account);

        return entityToDTO(updatedAccount);
    }

    private void validateRequest(CreateAccountRequest request) {
        if (Objects.isNull(request) || request.accountNumber() <= 0) {
            throw new IllegalArgumentException("Account number greater than 0 is required!");
        }

        if (request.balance() <= 0) {
            throw new IllegalArgumentException("Balance greater than 0 is required!");
        }

        try {
            Account account = getAccount.getByAccountNumber(request.accountNumber());

            if (Objects.nonNull(account)) {
                throw new AccountAlreadyExistsException("This account already exists!");
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Good, account does not exist yet");
        }
    }

    private AccountDTO entityToDTO(Account account) {
        return new AccountDTO(account.getNumber(), account.getBalance());
    }
}
