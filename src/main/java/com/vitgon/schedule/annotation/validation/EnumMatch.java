package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumMatch {
	Class<? extends Enum> enumClazz();
	String message() default "Enum value was not found!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
