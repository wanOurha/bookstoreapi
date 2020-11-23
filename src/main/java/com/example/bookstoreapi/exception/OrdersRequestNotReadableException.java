package com.example.bookstoreapi.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class OrdersRequestNotReadableException extends HttpMessageNotReadableException {

	public OrdersRequestNotReadableException() {
		super("Order request can not read.( null request )");
	}

}
