package com.example.bookstoreapi.exception;

public class UserDuplicateException extends RuntimeException {

	public UserDuplicateException(String username) {
		super(username + " :already exists.");
	}

}
