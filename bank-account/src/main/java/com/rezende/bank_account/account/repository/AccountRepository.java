package com.rezende.bank_account.account.repository;

import com.rezende.bank_account.account.model.Account;

public interface AccountRepository {

    void create(Account account);

    Account getByAccountNumber(long accountNumber);

    void updateBalance(Account account);
}
