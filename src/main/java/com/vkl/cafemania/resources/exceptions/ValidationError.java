package com.vkl.cafemania.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> Errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return Errors;
	}

	public void addError(String fieldName, String msg) {
		Errors.add(new FieldMessage(fieldName, msg));
	}
	


}
