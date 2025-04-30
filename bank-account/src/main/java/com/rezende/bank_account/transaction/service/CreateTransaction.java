package com.rezende.bank_account.transaction.service;

import com.rezende.bank_account.account.dto.AccountDTO;
import com.rezende.bank_account.account.model.Account;
import com.rezende.bank_account.account.service.CreateAccount;
import com.rezende.bank_account.account.service.GetAccount;
import com.rezende.bank_account.transaction.dto.CreateTransactionRequest;
import com.rezende.bank_account.transaction.model.PaymentType;
import com.rezende.bank_account.transaction.model.Transaction;
import com.rezende.bank_account.transaction.repository.CreateTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Objects;

@Service
public class CreateTransaction {

    private final GetAccount getAccount;
    private final CreateAccount createAccount;
    private final CreateTransactionRepository createTransactionRepository;

    public CreateTransaction(GetAccount getAccount, CreateAccount createAccount, CreateTransactionRepository createTransactionRepository) {
        this.getAccount = getAccount;
        this.createAccount = createAccount;
        this.createTransactionRepository = createTransactionRepository;
    }

    @Transactional
    public AccountDTO create(CreateTransactionRequest request) {
        validateRequest(request);

        Account account = getAccount.getByAccountNumber(request.accountNumber());

        Transaction transaction = new Transaction(1, PaymentType.getBySymbol(request.paymentType()), request.value());

        account.addTransaction(transaction);

        createTransactionRepository.save(transaction);
        return createAccount.update(account);
    }

    private void validateRequest(CreateTransactionRequest request) {
        if (Objects.isNull(request) || request.accountNumber() <= 0) {
            throw new IllegalArgumentException("Account number greater than 0 is required!");
        }

        if (request.value() <= 0) {
            throw new IllegalArgumentException("Value greater than 0 is required!");
        }

        PaymentType paymentType = PaymentType.getBySymbol(request.paymentType());

        if (Objects.isNull(paymentType)) {
            StringBuilder possibleValues = new StringBuilder();

            for (PaymentType pt : PaymentType.values()) {
                possibleValues.append(pt.getSymbol());
            }

            throw new IllegalArgumentException("Invalid payment type! Possible values are " + possibleValues);
        }
    }
}
