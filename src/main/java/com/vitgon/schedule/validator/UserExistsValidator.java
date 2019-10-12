package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.UserExists;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class UserExistsValidator implements ConstraintValidator<UserExists, Integer> {

	private UserService userService;

	public UserExistsValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<User> userOpt = userService.findById(id);
		if (userOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
