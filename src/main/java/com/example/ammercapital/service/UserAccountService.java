package com.example.ammercapital.service;

import com.example.ammercapital.controller.api.OperationRequest;
import com.example.ammercapital.domain.UserAccountEntity;
import com.example.ammercapital.domain.UserAccountOperationEntity;
import com.example.ammercapital.exceptions.NoAccountException;
import com.example.ammercapital.exceptions.NonPositiveException;
import com.example.ammercapital.exceptions.NotEnoughFundsException;
import com.example.ammercapital.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountOperationService userAccountOperationService;

    public UserAccountService(@Autowired UserAccountRepository userRepository,
                              @Autowired UserAccountOperationService userAccountOperationService) {
        this.userAccountRepository = userRepository;
        this.userAccountOperationService = userAccountOperationService;
    }

    public void deleteUserAccount(Long userAccountId) {
        userAccountRepository.deleteById(userAccountId);
    }

    public List<UserAccountEntity> getAllUserAccounts(Long userId) {
        return userAccountRepository.findAllByUserId(userId);
    }

    public void changeFundsByAccountId(OperationRequest operationRequest) {
        validateFunds(operationRequest);
        final Optional<UserAccountEntity> accountEntity = userAccountRepository.findById(operationRequest.getAccountId());
        final UserAccountEntity userAccountEntity = accountEntity.orElseThrow(() ->
                new NoAccountException("No account with id: " + operationRequest.getAccountId()));

        switch (operationRequest.getOperationType()) {
            case DEPOSIT:
                userAccountEntity.setBalance(userAccountEntity.getBalance().add(operationRequest.getAmount()));
                break;
            case WITHDRAW:
                final BigDecimal remains = userAccountEntity.getBalance().subtract(operationRequest.getAmount());
                if (remains.compareTo(BigDecimal.ZERO) < 0)
                    throw new NotEnoughFundsException("Not enough funds");
                userAccountEntity.setBalance(remains);
                break;
            default:
                throw new IllegalArgumentException("Not valid operation type");
        }
        userAccountRepository.save(userAccountEntity);
        userAccountOperationService.saveOperation(operationRequest);
    }

    private void validateFunds(OperationRequest operationRequest) {
        if (operationRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new NonPositiveException("Should be positive");
    }

    public UserAccountEntity getAccountById(Long accountId) {
        return userAccountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("No account with id: " + accountId));
    }

    public List<UserAccountOperationEntity> getAllOperationsByAccountId(Long accountId) {
        return userAccountOperationService.getAllOperationsByUserAccountId(accountId);
    }
}
