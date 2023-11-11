package com.softuni.mobilelele.repository;

import com.softuni.mobilelele.model.entity.UserActivationLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity, Long> {



}
