package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.BrandDTO;
import com.softuni.mobilelele.model.dto.ModelDTO;
import com.softuni.mobilelele.model.entity.ModelEntity;
import com.softuni.mobilelele.repository.ModelRepository;
import com.softuni.mobilelele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;

    public BrandServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public List<BrandDTO> getAllBrands() {

        Map<String, BrandDTO> brands = new TreeMap<>();

        for (ModelEntity model : modelRepository.findAll()) {
            if (!brands.containsKey(model.getBrand().getBrand())) {
                brands.put(model.getBrand().getBrand(), new BrandDTO(model.getBrand().getBrand(), new ArrayList<>()));
            }
            brands.get(model.getBrand().getBrand()).models().add(new ModelDTO(model.getId(), model.getName()));
        }
        return brands.values().stream().toList();
    }
}
