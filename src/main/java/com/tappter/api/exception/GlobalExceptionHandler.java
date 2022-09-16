package com.tappter.api.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {WrongUserRequestException.class})
	public ResponseEntity<Object> handleUserNotFondException(WrongUserRequestException e) {
		
		ApiException apiException = new ApiException(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}
}
