package com.example.bookstoreapi.controller.api;

import javax.validation.Valid;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.controller.request.OrderRequest;
import com.example.bookstoreapi.controller.response.PriceResponse;
import com.example.bookstoreapi.exception.ValidationException;
import com.example.bookstoreapi.service.OrdersServiceImplement;

@RestController
public class OrderController {

	private final OrdersServiceImplement ordersServiceImplement;

	public OrderController(OrdersServiceImplement ordersServiceImplement) {
		super();
		this.ordersServiceImplement = ordersServiceImplement;
	}

	@PostMapping(path = "/users/orders")
	public PriceResponse order(@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult,
			@RequestHeader("Authorization") String jwtToken) throws HttpMessageNotReadableException {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return ordersServiceImplement.saveOrder(orderRequest, jwtToken);
	}

}
