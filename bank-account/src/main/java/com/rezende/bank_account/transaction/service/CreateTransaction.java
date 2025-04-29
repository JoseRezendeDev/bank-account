package com.rezende.bank_account.transaction.service;

import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.service.GetAccount;
import com.rezende.bank_account.account.service.UpdateAccountBalance;
import com.rezende.bank_account.transaction.dto.CreateTransactionRequest;
import com.rezende.bank_account.transaction.model.PaymentType;
import com.rezende.bank_account.transaction.model.Transaction;
import com.rezende.bank_account.transaction.repository.CreateTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class CreateTransaction {

    private final GetAccount getAccount;
    private final UpdateAccountBalance updateAccountBalance;
    private final CreateTransactionRepository createTransactionRepository;

    public CreateTransaction(GetAccount getAccount, UpdateAccountBalance updateAccountBalance, CreateTransactionRepository createTransactionRepository) {
        this.getAccount = getAccount;
        this.updateAccountBalance = updateAccountBalance;
        this.createTransactionRepository = createTransactionRepository;
    }

    public void create(CreateTransactionRequest request) {
        validateRequest(request);

        Account account = getAccount.getByAccountNumber(request.accountNumber());

        Transaction transaction = new Transaction(account, PaymentType.valueOf(request.paymentType()), request.value());

        account.addTransaction(transaction);

        createTransactionRepository.createTransaction(transaction);
        updateAccountBalance.update(account);
    }

    private void validateRequest(CreateTransactionRequest request) {
        if (Objects.isNull(request) || request.accountNumber() <= 0) {
            throw new IllegalArgumentException("Account number greater than 0 is required!");
        }

        if (request.value() <= 0) {
            throw new IllegalArgumentException("Value greater than 0 is required!");
        }

        boolean isValidPaymentType = Arrays.stream(PaymentType.values()).anyMatch(paymentType -> paymentType.getSymbol().equals(request.paymentType()));

        if (!isValidPaymentType) {
            throw new IllegalArgumentException("Invalid payment type!");
        }
    }
}
