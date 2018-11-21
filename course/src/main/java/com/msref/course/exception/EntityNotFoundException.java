package com.msref.course.exception;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private int errorCode;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String errorMessage, int errorCode) {
		super(errorMessage);
		setErrorMessage(errorMessage);
		setErrorCode(errorCode);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	

}
