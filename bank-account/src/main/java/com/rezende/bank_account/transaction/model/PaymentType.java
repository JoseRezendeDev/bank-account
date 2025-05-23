package com.rezende.bank_account.transaction.model;

public enum PaymentType {
    PIX("P", 0f),
    CREDIT_CARD("C", 0.05f),
    DEBIT_CARD("D", 0.03f);

    private final String symbol;
    private final float tax;

    PaymentType(String symbol, float tax) {
        this.symbol = symbol;
        this.tax = tax;
    }

    public static PaymentType getBySymbol(String symbol) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getSymbol().equals(symbol)) {
                return paymentType;
            }
        }

        return null;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public float getTax() {
        return this.tax;
    }
}
