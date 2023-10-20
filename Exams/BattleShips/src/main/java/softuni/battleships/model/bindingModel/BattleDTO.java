package softuni.battleships.model.bindingModel;

public class BattleDTO {
    private Long attackerId;
    private Long defenderId;

    public BattleDTO() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public BattleDTO setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
        return this;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public BattleDTO setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
