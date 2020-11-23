package com.example.bookstoreapi.exception;

public class UserNameSyntaxException extends RuntimeException {

	public UserNameSyntaxException() {
		super("Username format not true ( Username format are name.surname ).");
	}

}
