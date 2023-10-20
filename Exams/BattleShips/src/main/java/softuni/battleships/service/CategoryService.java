package softuni.battleships.service;

import softuni.battleships.model.entity.CategoryEntity;
import softuni.battleships.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findByName(CategoryNameEnum category);
}
