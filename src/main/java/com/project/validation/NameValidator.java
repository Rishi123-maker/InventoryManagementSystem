package com.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {

		return name != null && name.matches("^[a-zA-Z\\s]+$");
	}

}
