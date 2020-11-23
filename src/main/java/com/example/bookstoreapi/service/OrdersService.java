package com.example.bookstoreapi.service;

import com.example.bookstoreapi.controller.request.OrderRequest;
import com.example.bookstoreapi.controller.response.PriceResponse;

public interface OrdersService {
	PriceResponse saveOrder(OrderRequest orderRequest, String jwtToken);

	void deleteOrderByUserId(String jwtToken);
}
