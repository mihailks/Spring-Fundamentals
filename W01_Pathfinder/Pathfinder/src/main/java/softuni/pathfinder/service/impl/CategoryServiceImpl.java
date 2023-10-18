package softuni.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.pathfinder.model.entity.Category;
import softuni.pathfinder.model.entity.enums.CategoryNameEnum;
import softuni.pathfinder.repository.CategoryRepository;
import softuni.pathfinder.service.CategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
