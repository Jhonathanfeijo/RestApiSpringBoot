package br.com.domain.validationConstraintValidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.domain.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList,List> {

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		return value!=null && !value.isEmpty();
	}
	@Override
	public void initialize(NotEmptyList annotation) {
		
	}

}
