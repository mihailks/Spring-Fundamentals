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
    @OneToOne
    private UserEntity user;

    public ShipEntity() {
    }


}
