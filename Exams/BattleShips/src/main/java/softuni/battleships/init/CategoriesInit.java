package softuni.battleships.init;

import org.springframework.boot.CommandLineRunner;
import softuni.battleships.service.CategoryService;

public class CategoriesInit implements CommandLineRunner {

    private final CategoryService categoryService;

    public CategoriesInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
    }
}
