package com.rezende.bank_account.account.model;

import com.rezende.bank_account.transaction.exception.InsufficientBalanceException;
import com.rezende.bank_account.transaction.model.PaymentType;
import com.rezende.bank_account.transaction.model.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    private long number;
    private float balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account() {
    }

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
        transaction.setAccount(this);
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public long getNumber() {
        return number;
    }

    public float getBalance() {
        return balance;
    }
}
