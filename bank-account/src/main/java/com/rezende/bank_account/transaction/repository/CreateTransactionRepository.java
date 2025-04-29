package com.rezende.bank_account.transaction.repository;

import com.rezende.bank_account.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateTransactionRepository extends JpaRepository<Transaction, Long> {

}
