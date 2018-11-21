package com.msref.course.model;

public class ErrorResponse {
	
	private int statusCode;
	
	private int errorCode;
	
	private String errorMessage;

	public ErrorResponse() {
	}

	public ErrorResponse(int statusCode, int errorCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}