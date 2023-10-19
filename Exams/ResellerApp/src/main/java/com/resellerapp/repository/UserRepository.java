package com.resellerapp.repository;

import com.resellerapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String value);
    Optional<UserEntity> findByUsername(String value);
    Optional<UserEntity> findFirstByUsernameAndPassword(String username, String password);
}
