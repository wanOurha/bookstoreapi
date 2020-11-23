package com.example.bookstoreapi.exception;

public class UnauthorizeException extends RuntimeException {

	public UnauthorizeException(String massage) {
		super(massage);
	}
}
