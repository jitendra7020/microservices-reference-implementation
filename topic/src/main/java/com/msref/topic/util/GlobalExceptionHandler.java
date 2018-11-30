package com.msref.topic.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.msref.topic.exception.EntityAlreadyExistsException;
import com.msref.topic.exception.EntityNotFoundException;
import com.msref.topic.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * method to handle entity not found exception
	 * @param entityNotFoundException
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> entityNotFoundExceptionExceptionHandler(EntityNotFoundException entityNotFoundException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorCode(entityNotFoundException.getErrorCode());
		errorResponse.setErrorMessage(entityNotFoundException.getErrorMessage());
		return buildResponseEntity(errorResponse);
	}
	
	/**
	 * method to handle entity already exists exception
	 * @param entityAlreadyExistsException
	 * @return
	 */
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> entityAlreadyExistsExceptionHandler(EntityAlreadyExistsException entityAlreadyExistsException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
		errorResponse.setErrorCode(entityAlreadyExistsException.getErrorCode());
		errorResponse.setErrorMessage(entityAlreadyExistsException.getErrorMessage());
		return buildResponseEntity(errorResponse);
	}
	
    /**
     * method to handle method argument not valid exception. This method will be invoked when the request bodies are validated (use of @valid annotation on controller methods)
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorResponse> errorResponseList = processFieldErrors(fieldErrors);
        return buildResponseEntity(errorResponseList, HttpStatus.BAD_REQUEST.value());
    }		

	/**
	 * This method is used to handle all other runtime exceptions thrown in the application and send back a 500 internal server error response to the client
	 * @param runtimeException
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException runtimeException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorCode(GlobalErrorHandlingConstants.UNDEFINED_ERROR_CODE);
		errorResponse.setErrorMessage(GlobalErrorHandlingConstants.UNDEFINED_ERROR_MESSAGE);
		return buildResponseEntity(errorResponse);
	}
	
    /**
     * This method is used to build the Response Entity for a single error response
     * @param errorResponse
     * @return
     */
    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
    }

    /**
     * This method is used to build the response Entity for multiple error responses. In this case, calling method has to send the desired http status code as 
     * there are multiple error response object available
     * @param errorResponseList
     * @return
     */
    private ResponseEntity<List<ErrorResponse>> buildResponseEntity(List<ErrorResponse> errorResponseList, Integer httpStatusCode) {
        return new ResponseEntity<>(errorResponseList, HttpStatus.valueOf(httpStatusCode));
    }    

    /**
     * This helper method is used at this point to build error responses for parameter validation errors
     * @param fieldErrors
     * @return
     */
    private List<ErrorResponse> processFieldErrors(List<FieldError> fieldErrors) {
        List<ErrorResponse> errorResponseList = new ArrayList<ErrorResponse>();
    	for (FieldError fieldError: fieldErrors) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setErrorCode(GlobalErrorHandlingConstants.BAD_REQUEST_ERROR_CODE);
    		errorResponse.setErrorMessage(fieldError.getField() + ": " +fieldError.getDefaultMessage());
            errorResponseList.add(errorResponse);
        }
        return errorResponseList;
    }    
}
