package com.sapient.app.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sapient.app.exception.CapitalDataServiceException;

@ControllerAdvice
public class CapitalDataExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(CapitalDataExceptionController.class);

	@ExceptionHandler(value = CapitalDataServiceException.class)
	public ResponseEntity<Object> exception(CapitalDataServiceException exception) {
		logger.error("CapitalDataServiceException exception occurred. Cause {}", exception);
		return new ResponseEntity<>(String.format("Request Failed. Cause : %s", exception.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
