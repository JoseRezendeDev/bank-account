package com.rezende.bank_account.util;

import com.rezende.bank_account.account.exception.AccountAlreadyExistsException;
import com.rezende.bank_account.account.exception.AccountNotFoundException;
import com.rezende.bank_account.transaction.exception.InsufficientBalanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Throwable t) {
        return ResponseEntity.status(400).body(new ErrorResponse(400, t.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(Throwable t) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, t.getMessage()));
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountAlreadyExistsException(Throwable t) {
        return ResponseEntity.status(400).body(new ErrorResponse(400, t.getMessage()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(Throwable t) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, t.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Throwable t) {
        return ResponseEntity.status(500).body(new ErrorResponse(500, t.getMessage()));
    }
}
