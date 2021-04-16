package com.example.ammercapital.repository;

import com.example.ammercapital.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Override
    List<UserEntity> findAll();
}
