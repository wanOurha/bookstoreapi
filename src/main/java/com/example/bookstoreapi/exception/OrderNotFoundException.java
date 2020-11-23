package com.example.bookstoreapi.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(long id) {
		super(id + "  :not founds.");
	}
}
