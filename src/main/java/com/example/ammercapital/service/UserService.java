package com.example.ammercapital.service;

import com.example.ammercapital.domain.UserAccountEntity;
import com.example.ammercapital.domain.UserEntity;
import com.example.ammercapital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAccountService userAccountService;

    public UserService(@Autowired UserRepository userRepository, @Autowired UserAccountService userAccountService) {
        this.userRepository = userRepository;
        this.userAccountService = userAccountService;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<BigDecimal> getTotalBalanceByUserId(Long userId) {
        final List<UserAccountEntity> allUserAccounts = userAccountService.getAllUserAccounts(userId);
        return allUserAccounts.stream().map(UserAccountEntity::getBalance).reduce(BigDecimal::add);
    }
}
