package softuni.coffeeshop.model.binding;

import softuni.coffeeshop.model.entity.Category;
import softuni.coffeeshop.model.entity.enums.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddBindingModelClass {
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryNameEnum category;
    private String description;
}
