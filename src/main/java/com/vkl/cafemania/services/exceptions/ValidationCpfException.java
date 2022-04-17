package com.vkl.cafemania.services.exceptions;

public class ValidationCpfException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidationCpfException(String msg) {
		super(msg);
	}
	public ValidationCpfException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
