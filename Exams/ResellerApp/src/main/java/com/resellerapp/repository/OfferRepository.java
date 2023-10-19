package com.resellerapp.repository;

import com.resellerapp.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findByCreatedBy_IdAndBoughtByIsNull(Long createdBy_id);

    List<OfferEntity> findByBoughtBy_Id(Long boughtBy_id);

    List<OfferEntity> findAllByBoughtByIsNullAndCreatedBy_IdIsNot(Long createdBy_id);


}
