package com.teamway.test.workersapp.domain.exception;

public class ShiftNotFoundException extends RuntimeException {

	public ShiftNotFoundException(String message) {
		super(message);
	}

	public ShiftNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
