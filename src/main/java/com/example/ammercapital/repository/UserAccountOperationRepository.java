package com.example.ammercapital.repository;

import com.example.ammercapital.domain.UserAccountOperationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAccountOperationRepository extends CrudRepository<UserAccountOperationEntity, Long> {
    @Override
    List<UserAccountOperationEntity> findAll();

    List<UserAccountOperationEntity> findAllByAccountId(Long id);
}
