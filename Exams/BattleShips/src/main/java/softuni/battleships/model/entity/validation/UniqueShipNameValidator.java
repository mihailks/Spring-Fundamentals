package softuni.battleships.model.entity.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import softuni.battleships.repository.ShipRepository;

public class UniqueShipNameValidator implements ConstraintValidator<UniqueShipName, String> {
    private final ShipRepository shipRepository;

    public UniqueShipNameValidator(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isFound = shipRepository.findByName(value).isEmpty();
        return isFound;
    }
}
