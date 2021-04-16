package com.example.ammercapital.controller;

import com.example.ammercapital.controller.api.DepositRequest;
import com.example.ammercapital.controller.api.WithdrawRequest;
import com.example.ammercapital.domain.UserAccountEntity;
import com.example.ammercapital.domain.UserAccountOperationEntity;
import com.example.ammercapital.exceptions.NoAccountException;
import com.example.ammercapital.exceptions.NonPositiveException;
import com.example.ammercapital.exceptions.NotEnoughFundsException;
import com.example.ammercapital.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user/account")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(@Autowired UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    @DeleteMapping("/remove/{accountId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long accountId){
        try {
            userAccountService.deleteUserAccount(accountId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/{userId}")
    public List<UserAccountEntity> getAllUsersAccounts(@PathVariable Long userId){
        return userAccountService.getAllUserAccounts(userId);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositToAccount(@RequestBody DepositRequest depositRequest){
        try{
            userAccountService.changeFundsByAccountId(depositRequest);
            return ResponseEntity.ok().build();
        }catch (NonPositiveException | IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch (NoAccountException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFromAccount(@RequestBody WithdrawRequest withdrawRequest){
        try{
            userAccountService.changeFundsByAccountId(withdrawRequest);
            return ResponseEntity.ok().build();
        }catch (NonPositiveException | NotEnoughFundsException | IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch (NoAccountException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance/{accountId}")
    public BigDecimal getAccountBalance(@PathVariable Long accountId){
        return userAccountService.getAccountById(accountId).getBalance();
    }

    @GetMapping("/operations/{accountId}")
    public List<UserAccountOperationEntity> getAllUserAccountOperations(@PathVariable Long accountId){
        return userAccountService.getAllOperationsByAccountId(accountId);
    }
}
