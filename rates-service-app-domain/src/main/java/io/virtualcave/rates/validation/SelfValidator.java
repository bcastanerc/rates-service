package io.virtualcave.rates.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Validator abstract class for validate beans itself.
 *
 * @param <T> Class type of the inheriting bean.
 */
public abstract class SelfValidator<T> {

  private final Validator validator;

  public SelfValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * Evaluates all Bean Validations on the attributes of this instance.
   */
  public void validateSelf() {
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
