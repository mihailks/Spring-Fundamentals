package softuni.battleships.model.entity.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueShipNameValidator.class)
public @interface UniqueShipName {
    String message() default "Ship already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
