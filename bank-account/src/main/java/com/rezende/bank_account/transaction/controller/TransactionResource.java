package com.rezende.bank_account.transaction.controller;

import com.rezende.bank_account.account.dto.AccountDTO;
import com.rezende.bank_account.transaction.dto.CreateTransactionRequest;
import com.rezende.bank_account.transaction.service.CreateTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    private final CreateTransaction createTransaction;

    public TransactionResource(CreateTransaction createTransaction) {
        this.createTransaction = createTransaction;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        AccountDTO accountDTO = createTransaction.create(createTransactionRequest);
        return ResponseEntity.status(201).body(accountDTO);
    }
}
