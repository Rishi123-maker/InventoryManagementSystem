package com.project.validation;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
	String message() default "Name should not contain symbols or numbers";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
