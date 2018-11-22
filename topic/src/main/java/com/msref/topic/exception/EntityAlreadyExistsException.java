package com.msref.topic.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private int errorCode;

	public EntityAlreadyExistsException() {
		super();
	}

	public EntityAlreadyExistsException(String errorMessage, int errorCode) {
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
