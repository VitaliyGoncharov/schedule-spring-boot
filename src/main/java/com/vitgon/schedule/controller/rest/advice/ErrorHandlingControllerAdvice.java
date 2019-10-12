package com.vitgon.schedule.controller.rest.advice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.service.MessageService;

import lombok.AllArgsConstructor;


@RestController
@ControllerAdvice
public class ErrorHandlingControllerAdvice {
	
	private MessageService messageService;

	public ErrorHandlingControllerAdvice(MessageService messageService) {
		this.messageService = messageService;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	ApiError onException(Exception e) {
		return new ApiError(new Date(), e.getMessage(), null);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ApiError onNoHandlerFoundException(NoHandlerFoundException e) {
		return new ApiError(new Date(), "Resource Not Found", "The resource was not found!");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ApiError onMethodArgumentNotValid(MethodArgumentNotValidException e) {
		List<Violation> violations = new ArrayList<>();
		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			Violation fieldViolation = null;
			// check if violation for this field was created before
			for (Violation violation : violations) {
				if (violation.getFieldName().equals(fieldError.getField())) {
					fieldViolation = violation;
					break;
				}
			}
			
			// if field violation was created before, then add extra error message,
			// otherwise create field violation
			if (fieldViolation != null) {
				fieldViolation.getMessages().add(fieldError.getDefaultMessage());
			} else {
				// important to create mutable array: new ArrayList<>(Arrays.asList(...))
				violations.add(new Violation(fieldError.getField(), new ArrayList<>(Arrays.asList(fieldError.getDefaultMessage()))));
			}
		}
		return new ApiError(new Date(), "Validation Failed", violations);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ApiError onConstraintViolationException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		Violation violation = null;
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			if (violation == null) {
				violation = new Violation(
						constraintViolation.getPropertyPath().toString(),
						Arrays.asList(constraintViolation.getMessage()));
			} else {
				violation.getMessages().add(constraintViolation.getMessage());
			}
		}
		return new ApiError(new Date(), "Validation Failed", violations);
	}
	
	@ExceptionHandler(FieldValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ApiError onFieldValidationException(FieldValidationException e) {
		String i18nCode = e.getI18nCode();
		String fieldName = e.getFieldName();
		List<Violation> violations = new ArrayList<>();
		violations.add(new Violation(fieldName, Arrays.asList(messageService.getMessage(i18nCode))));
		return new ApiError(new Date(), "Validation Failed", violations);
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ApiError onBindException(BindException e) {
		List<Violation> violations = new ArrayList<>();
		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			Violation fieldViolation = null;
			// check if violation for this field was created before
			for (Violation violation : violations) {
				if (violation.getFieldName().equals(fieldError.getField())) {
					fieldViolation = violation;
					break;
				}
			}
			
			// if field violation was created before, then add extra error message,
			// otherwise create field violation
			if (fieldViolation != null) {
				fieldViolation.getMessages().add(fieldError.getDefaultMessage());
			} else {
				violations.add(new Violation(fieldError.getField(), Arrays.asList(fieldError.getDefaultMessage())));
			}
		}
		return new ApiError(new Date(), "Validation Failed", violations);
	}
}
