package nav;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Data
@AllArgsConstructor
public class IsValidTypeValidator implements ConstraintValidator <IsValidType, String> {


    private NavService navService;



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return  navService.hasValidType(value);
    }
}
