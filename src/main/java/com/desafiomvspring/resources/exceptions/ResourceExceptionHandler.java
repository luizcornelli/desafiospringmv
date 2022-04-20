package com.desafiomvspring.resources.exceptions;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafiomvspring.services.exceptions.DatabaseException;
import com.desafiomvspring.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError standardError = new StandardError();

		standardError.setTimestamp(new Timestamp(System.currentTimeMillis()));
		standardError.setStatus(httpStatus.value());
		standardError.setError("Resource Not found");
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(standardError);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError();

		standardError.setTimestamp(new Timestamp(System.currentTimeMillis()));
		standardError.setStatus(httpStatus.value());
		standardError.setError("Database Exception");
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(standardError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError validationError = new ValidationError(new Timestamp(System.currentTimeMillis()),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {

			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}

}
