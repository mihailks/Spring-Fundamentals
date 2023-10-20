package softuni.battleships.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.battleships.service.CategoryService;
@Component
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
