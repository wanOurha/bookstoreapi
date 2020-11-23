package com.example.bookstoreapi.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String username) {
		super(username + " :not founds.");
	}

	public UserNotFoundException(long user_id) {
		super(user_id + " :not founds.");
	}

}
