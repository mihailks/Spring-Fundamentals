package softuni.pathfinder.service;

import softuni.pathfinder.model.entity.Category;
import softuni.pathfinder.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
