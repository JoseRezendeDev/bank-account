package com.rezende.bank_account.transaction.dto;

public record CreateTransactionRequest(long accountNumber, float value, String paymentType) {
}
