package com.srtech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHAndler {

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<?>  handleRuntimeExcception(RuntimeException exception){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
}
