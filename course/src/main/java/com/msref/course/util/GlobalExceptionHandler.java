package com.msref.course.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.msref.course.exception.EntityAlreadyExistsException;
import com.msref.course.exception.EntityNotFoundException;
import com.msref.course.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	ErrorResponse errorResponse;

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> entityNotFoundExceptionExceptionHandler(EntityNotFoundException entityNotFoundException) {
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorCode(entityNotFoundException.getErrorCode());
		errorResponse.setErrorMessage(entityNotFoundException.getErrorMessage());
		return buildResponseEntity(errorResponse);
	}
	
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> entityAlreadyExistsExceptionHandler(EntityAlreadyExistsException entityAlreadyExistsException) {
		errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
		errorResponse.setErrorCode(entityAlreadyExistsException.getErrorCode());
		errorResponse.setErrorMessage(entityAlreadyExistsException.getErrorMessage());
		return buildResponseEntity(errorResponse);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException runtimeException) {
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorCode(GlobalErrorHandlingConstants.UNDEFINED_ERROR_CODE);
		errorResponse.setErrorMessage(GlobalErrorHandlingConstants.UNDEFINED_ERROR_MESSAGE);
		return buildResponseEntity(errorResponse);
	}
	
    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
    }

}
