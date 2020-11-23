package com.example.bookstoreapi.exception;

public class BookQuantityException extends RuntimeException {

	public BookQuantityException(int book_quantity) {
		super(book_quantity + " are invalid quantity( quantity must more than 0 ).");
	}

}
