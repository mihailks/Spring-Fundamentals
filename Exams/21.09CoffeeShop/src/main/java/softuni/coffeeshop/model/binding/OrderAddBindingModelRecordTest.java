package softuni.coffeeshop.model.binding;

import softuni.coffeeshop.model.entity.enums.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderAddBindingModelRecordTest(String name, BigDecimal price, LocalDateTime orderTime, CategoryNameEnum category,
                                             String description) {
}
