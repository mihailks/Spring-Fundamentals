package softuni.coffeeshop.service;

import softuni.coffeeshop.model.entity.Category;
import softuni.coffeeshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
