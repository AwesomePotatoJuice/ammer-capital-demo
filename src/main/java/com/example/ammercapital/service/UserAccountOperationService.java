package com.example.ammercapital.service;

import com.example.ammercapital.controller.api.OperationRequest;
import com.example.ammercapital.domain.UserAccountOperationEntity;
import com.example.ammercapital.repository.UserAccountOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountOperationService {

    private final UserAccountOperationRepository userAccountOperationRepository;

    public UserAccountOperationService(@Autowired UserAccountOperationRepository userAccountOperationRepository) {
        this.userAccountOperationRepository = userAccountOperationRepository;
    }

    public List<UserAccountOperationEntity> getAllOperationsByUserAccountId(Long id) {
        return userAccountOperationRepository.findAllByAccountId(id);
    }

    public void saveOperation(OperationRequest operationRequest) {
        userAccountOperationRepository.save(
                new UserAccountOperationEntity(operationRequest.getAmount(), operationRequest.getAccountId(), operationRequest.getOperationType()));
    }
}
