package com.banking_transaction_api.banking_api.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
