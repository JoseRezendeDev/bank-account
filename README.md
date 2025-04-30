REST API for manage bank account and transaction. It persists data on an embedded database (H2)

POST /account -> Create account
Body: {
    "accountNumber": 123,
    "balance": 200.0
}


POST /transaction -> Create transaction
Body: {
    "accountNumber": 123,
    "value": 10.0,
    "paymentType": "C"
}

GET /account?accountNumber=123 -> Get account
