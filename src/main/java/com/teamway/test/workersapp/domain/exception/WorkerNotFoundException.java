package com.teamway.test.workersapp.domain.exception;

public class WorkerNotFoundException extends RuntimeException {

	public WorkerNotFoundException(String message) {
		super(message);
	}

	public WorkerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
