package locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;


    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value >= type.getMin() && value <= type.getMax();
    }

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        type = constraintAnnotation.type();
    }
}
