package softuni.battleships.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Long health;
    @Column(nullable = false)
    private Long power;
    @Column(nullable = false)
    private LocalDate created;
    @ManyToOne
    private CategoryEntity category;
    @ManyToOne
    private UserEntity user;

    public ShipEntity() {
    }

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public ShipEntity setHealth(Long health) {
        this.health = health;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public ShipEntity setPower(Long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
