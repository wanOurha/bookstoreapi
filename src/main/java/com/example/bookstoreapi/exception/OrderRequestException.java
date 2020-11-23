package com.example.bookstoreapi.exception;

public class OrderRequestException extends RuntimeException {

	public OrderRequestException() {
		super("Order request error( Request format: {\r\n" + "    \"orders\":	[1,2]\r\n"
				+ "} 1 are book_quantiy and must more than 0, 2 are book_id )");
	}

}
