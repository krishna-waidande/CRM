package com.krishagni.crm.exception;

import java.util.ArrayList;
import java.util.List;

public class CRMException extends RuntimeException {
	private List<String> errors = new ArrayList<String>();
	
	public CRMException() {}
	
	public CRMException(String error) {
		errors.add(error);
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		errors.add(error);
	}

	public void checkAndThrow() {
		if (errors.isEmpty()) {
			return;
		}
		
		throw this;	
	}
}