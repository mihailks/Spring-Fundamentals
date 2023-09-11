package softuni.coffeeshop.service.impl;

import org.springframework.stereotype.Service;
import softuni.coffeeshop.model.entity.Category;
import softuni.coffeeshop.model.entity.enums.CategoryNameEnum;
import softuni.coffeeshop.repository.CategoryRepository;
import softuni.coffeeshop.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setName(categoryNameEnum);
                    switch (categoryNameEnum) {

                        case COFFEE -> category.setNeededTime(2);
                        case CAKE -> category.setNeededTime(10);
                        case DRINK -> category.setNeededTime(1);
                        case OTHER -> category.setNeededTime(5);
                    }
                    categoryRepository.save(category);
                });


    }
}
