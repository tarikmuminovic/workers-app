package com.teamway.test.workersapp.rest.exception;

import com.teamway.test.workersapp.domain.exception.ShiftAlreadyAssignedException;
import com.teamway.test.workersapp.domain.exception.ShiftNotFoundException;
import com.teamway.test.workersapp.domain.exception.WorkerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ShiftAlreadyAssignedException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected RestApiExceptionResponse handleMultipleShifsNotSupportedException(Exception exception) {
		return handleException(exception, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {WorkerNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected RestApiExceptionResponse handleWorkerNotFoundException(Exception exception) {
		return handleException(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {ShiftNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected RestApiExceptionResponse handleShiftNotFoundException(Exception exception) {
		return handleException(exception, HttpStatus.NOT_FOUND);
	}

	private RestApiExceptionResponse handleException(Exception exception, HttpStatus status) {
		return new RestApiExceptionResponse(exception.getClass().getSimpleName(), exception.getMessage(),
				status.value());
	}
}