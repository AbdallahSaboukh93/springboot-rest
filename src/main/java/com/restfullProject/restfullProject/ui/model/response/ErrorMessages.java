package com.restfullProject.restfullProject.ui.model.response;



public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("missing required field , please check documentation for required field");

	private String errorMessage;



	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public String getErrorMessage() {
		return errorMessage;
	}

}
