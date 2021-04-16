package com.example.ammercapital.controller.api;

import com.example.ammercapital.dicts.OperationType;

import java.math.BigDecimal;

public interface OperationRequest {
    Long getAccountId();

    BigDecimal getAmount();

    OperationType getOperationType();
}
