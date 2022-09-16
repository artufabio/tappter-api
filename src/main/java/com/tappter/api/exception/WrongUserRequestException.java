package com.tappter.api.exception;

public class WrongUserRequestException extends RuntimeException{


	public WrongUserRequestException(String message) {
		super(message);
	}

}
