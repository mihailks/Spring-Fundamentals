package softuni.battleships.service.impl;

import org.springframework.stereotype.Service;
import softuni.battleships.model.entity.CategoryEntity;
import softuni.battleships.model.entity.enums.CategoryNameEnum;
import softuni.battleships.repository.CategoryRepository;
import softuni.battleships.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(c -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(c);
                    switch (c.name()) {
                        case "BATTLE", "CARGO", "PATROL" -> category.setDescription("Some description");
                    }
                    this.categoryRepository.save(category);
                });
    }

    @Override
    public CategoryEntity findByName(CategoryNameEnum category) {
        return categoryRepository.findByName(category);
    }


}
