package com.rezende.bank_account.account.controller;

import com.rezende.bank_account.account.dto.AccountDTO;
import com.rezende.bank_account.account.dto.CreateAccountRequest;
import com.rezende.bank_account.account.service.CreateAccount;
import com.rezende.bank_account.account.service.GetAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountResource {

    private final GetAccount getAccount;
    private final CreateAccount createAccount;

    public AccountResource(GetAccount getAccount, CreateAccount createAccount) {
        this.getAccount = getAccount;
        this.createAccount = createAccount;
    }

    @GetMapping
    public ResponseEntity<AccountDTO> getAccountByNumber(@RequestParam long accountNumber) {
        return ResponseEntity.ok(getAccount.getByAccountNumberAsDTO(accountNumber));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        AccountDTO createdAccount = createAccount.create(createAccountRequest);
        return ResponseEntity.status(201).body(createdAccount);
    }
}
