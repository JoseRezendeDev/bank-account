package com.rezende.bank_account.transaction.model;

import com.rezende.bank_account.account.model.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "account_transaction")
public class Transaction {
    @Id
    private long id;

    @JoinColumn(name = "account_number")
    @ManyToOne
    private Account account;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "transaction_value")
    private float value;

    public Transaction() {
    }

    public Transaction(long id, PaymentType paymentType, float value) {
        this.id = id;
        this.paymentType = paymentType;
        this.value = value;
    }

    public void setAccount(Account account) {
        this.account = account;
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
