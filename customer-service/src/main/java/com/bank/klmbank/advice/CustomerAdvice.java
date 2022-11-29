package com.bank.klmbank.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerAdvice {
	@ExceptionHandler(CustomerException.class)
	 public ResponseEntity<?> handleBookException(CustomerException customerException) {
        return new ResponseEntity<String>("BookException: "+customerException.getMessage(), HttpStatus.OK);
    }

}
