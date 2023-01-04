package shpp.com.ua.example.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = IpnValidatorImpl.class)
public @interface IpnValidator {
    String message() default "{shpp.com.ua.example.validator" +
            "message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
