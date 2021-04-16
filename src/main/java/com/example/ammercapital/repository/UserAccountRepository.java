package com.example.ammercapital.repository;

import com.example.ammercapital.domain.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {
    @Override
    List<UserAccountEntity> findAll();

    List<UserAccountEntity> findAllByUserId(Long userId);

}
