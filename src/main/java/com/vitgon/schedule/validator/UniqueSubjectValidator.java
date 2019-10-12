package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.UniqueSubject;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSubjectValidator implements ConstraintValidator<UniqueSubject, String> {
	
	private SubjectService subjectService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Subject subject = subjectService.findByName(value.toLowerCase());
		if (subject == null) {
			return true;
		}
		return false;
	}
}
