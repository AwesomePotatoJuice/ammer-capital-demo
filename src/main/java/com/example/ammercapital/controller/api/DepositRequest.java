package com.example.ammercapital.controller.api;

import com.example.ammercapital.dicts.OperationType;

import java.math.BigDecimal;

public class DepositRequest implements OperationRequest {
    private final Long accountId;
    private final BigDecimal amount;
    private final OperationType operationType = OperationType.DEPOSIT;

    public DepositRequest(Long accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
