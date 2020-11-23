package com.example.bookstoreapi.exception;

public class BookIdNotFoundException extends RuntimeException {

	public BookIdNotFoundException(int book_id) {
		super("book id: " + book_id + " not founds.");
	}

}