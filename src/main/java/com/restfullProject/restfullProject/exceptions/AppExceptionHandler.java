package com.restfullProject.restfullProject.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.restfullProject.restfullProject.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}


}
