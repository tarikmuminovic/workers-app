package com.teamway.test.workersapp.domain.exception;

public class ShiftAlreadyAssignedException extends RuntimeException {

	public ShiftAlreadyAssignedException(String message) {
		super(message);
	}

	public ShiftAlreadyAssignedException(String message, Throwable cause) {
		super(message, cause);
	}
}
