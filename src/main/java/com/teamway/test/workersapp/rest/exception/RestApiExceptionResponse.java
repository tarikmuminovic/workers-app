package com.teamway.test.workersapp.rest.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RestApiExceptionResponse(@JsonProperty("exception") String exception,
									   @JsonProperty("message") String message,
									   @JsonProperty("status") Integer status) {

}
