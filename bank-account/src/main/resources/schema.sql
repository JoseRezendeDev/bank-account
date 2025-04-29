CREATE TABLE account (
    number BIGINT PRIMARY KEY,
    balance DECIMAL(15, 2) NOT NULL
);

CREATE TABLE account_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_type VARCHAR(255) NOT NULL,
    transaction_value DECIMAL(15, 2) NOT NULL,
    account_number BIGINT,
    FOREIGN KEY (account_number) REFERENCES account(number)
);