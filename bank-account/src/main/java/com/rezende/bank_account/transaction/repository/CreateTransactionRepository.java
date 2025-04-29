package com.rezende.bank_account.transaction.repository;

import com.rezende.bank_account.transaction.model.Transaction;

public interface CreateTransactionRepository {

    void createTransaction(Transaction transaction);
}
