package com.example.ammercapital.controller;

import com.example.ammercapital.domain.UserEntity;
import com.example.ammercapital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BigDecimal> getTotalUserBalance(@PathVariable Long userId) {
        return ResponseEntity.of(userService.getTotalBalanceByUserId(userId));
    }
}
