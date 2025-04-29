package com.rezende.bank_account.account.service;

import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountBalance {

    private final AccountRepository accountRepository;

    public UpdateAccountBalance(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void update(Account account) {
        accountRepository.updateBalance(account);
    }
}
