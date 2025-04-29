package com.rezende.bank_account.transaction.model;

import com.rezende.bank_account.account.model.Account;

public class Transaction {
    private Account account;
    private PaymentType paymentType;
    private float value;

    public Transaction(Account account, PaymentType paymentType, float value) {
        this.account = account;
        this.paymentType = paymentType;
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public float getValue() {
        return value;
    }
}
