package nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidTaxNumberValidator implements ConstraintValidator<IsValidTaxNumber, String> {


    TaxNumberValidator pojo = new TaxNumberValidator();


    @Override
    public void initialize(IsValidTaxNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return pojo.check(value);
        }
        catch (IllegalArgumentException iae) {
            return  false;
        }

    }
}
