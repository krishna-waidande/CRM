package com.krishagni.crm.exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyExceptionHandler {
	@ExceptionHandler(CRMException.class)
	public ResponseEntity<List<String>> handleCRMException(CRMException crmEx) {
		return new ResponseEntity<List<String>>(crmEx.getErrors(), HttpStatus.OK);
	}
}