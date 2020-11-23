package com.example.bookstoreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerUserNotFoundException(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerUserDuplicateException(UserDuplicateException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerDateFormatException(DateFormatException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerValidationException(ValidationException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerOrderNotFoundException(OrderNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerBookIdNotFoundException(BookIdNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerBookQuantityException(BookQuantityException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerOrderRequestException(OrderRequestException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerOrdersRequestNotReadableException(OrdersRequestNotReadableException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerUserNameSyntaxException(UserNameSyntaxException ex) {
		return ex.getMessage();
	}

}
