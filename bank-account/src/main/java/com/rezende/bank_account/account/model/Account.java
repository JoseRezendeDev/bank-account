package com.rezende.bank_account.account.model;

import com.rezende.bank_account.transaction.exception.InsufficientBalanceException;
import com.rezende.bank_account.transaction.model.PaymentType;
import com.rezende.bank_account.transaction.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final long number;
    private float balance;
    private final List<Transaction> transactions;

    public Account(long number, float balance) {
        this.number = number;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        PaymentType paymentType = transaction.getPaymentType();
        float totalWithTax = transaction.getValue() + transaction.getValue() * paymentType.getTax();

        if (this.balance < totalWithTax) {
            throw new InsufficientBalanceException("The balance is insufficient!");
        }

        this.balance = this.balance - totalWithTax;
        this.transactions.add(transaction);
    }

    public long getNumber() {
        return number;
    }

    public float getBalance() {
        return balance;
    }
}
