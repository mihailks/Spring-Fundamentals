package com.resellerapp.model.entity;

import com.resellerapp.model.entity.enums.ConditionNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class ConditionEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ConditionNameEnum name;
    @Column(name = "description", nullable = false)
    private String description;

    public ConditionEntity() {
    }

    public ConditionNameEnum getName() {
        return name;
    }

    public ConditionEntity setName(ConditionNameEnum name) {
        this.name = name;
        this.setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }

//    public Condition setDescription(String description) {
//        this.description = description;
//        return this;
//    }

    private void setDescription(ConditionNameEnum name) {
        String description = "";
        switch (name) {
            case EXCELLENT:
                description = "In perfect condition";
                break;
            case GOOD:
                description = "Some signs of wear and tear or minor defects";
                break;
            case ACCEPTABLE:
                description = "The item is fairly worn but continues to function properly";
                break;
        }
        this.description = description;
    }
}
