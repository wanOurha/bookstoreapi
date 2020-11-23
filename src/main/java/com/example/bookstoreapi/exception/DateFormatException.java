package com.example.bookstoreapi.exception;

public class DateFormatException extends RuntimeException {

	public DateFormatException() {
		super("Date format not match dd/MM/yyyy .");
	}

}
