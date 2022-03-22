package io.virtualcave.rates.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CurrencyIsoCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyIsoCode {

  String INVALID_CURRENCY_MSG = "Invalid ISO 4217 currency code.";

  String message() default INVALID_CURRENCY_MSG;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
