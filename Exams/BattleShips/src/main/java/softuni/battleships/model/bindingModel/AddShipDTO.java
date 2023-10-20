package softuni.battleships.model.bindingModel;

import jakarta.validation.constraints.*;
import softuni.battleships.model.entity.enums.CategoryNameEnum;
import softuni.battleships.model.entity.validation.UniqueShipName;

import java.time.LocalDate;

public class AddShipDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters long.")
    @UniqueShipName
    private String name;
    @NotNull
    @Positive
    private Integer health;
    @NotNull
    @Positive
    private Integer power;
    @NotNull
    @PastOrPresent
    private LocalDate date;
    @NotNull
    private CategoryNameEnum category;

    public AddShipDTO() {
    }

    public Long getId() {
        return id;
    }

    public AddShipDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public AddShipDTO setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public AddShipDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public AddShipDTO setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public AddShipDTO setPower(Integer power) {
        this.power = power;
        return this;
    }
}
