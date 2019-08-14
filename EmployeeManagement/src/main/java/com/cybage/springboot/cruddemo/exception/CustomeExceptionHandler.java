package com.cybage.springboot.cruddemo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(RecordNotFoundException.class)
	    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Record Not Found", details);
	        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	    }
	 
	 
	 @ExceptionHandler(NoRecordWithFirstNameException.class)
	    public final ResponseEntity<Object> handleUserNotFoundWithFirstNameException(NoRecordWithFirstNameException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("No employees found with first name", details);
	        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	    }
	
}
