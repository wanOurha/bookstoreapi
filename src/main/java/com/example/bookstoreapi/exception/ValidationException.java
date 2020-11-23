package com.example.bookstoreapi.exception;

public class ValidationException extends RuntimeException {

	public ValidationException(String massage) {
		super(massage + " are requried");
	}

}
