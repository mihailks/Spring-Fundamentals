package com.softuni.mobilelele.repository;

import com.softuni.mobilelele.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
//    @Query("Select b FROM BrandEntity b JOIN FETCH b.models")
//    public List<BrandEntity> getAllBrands();

    @EntityGraph(value = "brandWithModels",
    attributePaths = "models")
    @Query("Select b from BrandEntity b")
    List<BrandEntity> getAllBrands();


}
