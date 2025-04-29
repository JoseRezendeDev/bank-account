package com.rezende.bank_account.transaction.controller;

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

//    @GetMapping
//    public ResponseEntity<Transaction> getTransactionByNumber(@RequestParam long accountNumber) {
//        return ResponseEntity.ok(getTransaction.ge(accountNumber));
//    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        createTransaction.create(createTransactionRequest);
        return ResponseEntity.ok().build();
    }
}
