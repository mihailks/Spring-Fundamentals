package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Collection<UserEntity> findByUsername(String value);

    Collection<UserEntity> findByEmail(String value);

    Optional<UserEntity> findFirstByUsernameAndPassword(String username, String password);
}
