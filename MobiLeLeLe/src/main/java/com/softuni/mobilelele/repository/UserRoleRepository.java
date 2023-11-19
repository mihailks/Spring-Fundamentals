package com.softuni.mobilelele.repository;

import com.softuni.mobilelele.model.entity.UserRoleEntity;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{
    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);
}
