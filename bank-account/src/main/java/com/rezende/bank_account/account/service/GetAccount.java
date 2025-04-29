package com.rezende.bank_account.account.service;

import com.rezende.bank_account.account.dto.AccountDTO;
import com.rezende.bank_account.account.exception.AccountNotFoundException;
import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetAccount {

    private final AccountRepository accountRepository;

    public GetAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getByAccountNumber(long number) {
        Account account = accountRepository.getByAccountNumber(number);

        if (Objects.isNull(account)) {
            throw new AccountNotFoundException("Account number " + number + " not found!");
        }

//        return entityToDTO(account);
        return account;
    }

    private AccountDTO entityToDTO(Account account) {
        return new AccountDTO(account.getNumber(), account.getBalance());
    }
}
