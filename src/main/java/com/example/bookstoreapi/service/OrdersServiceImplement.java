package com.example.bookstoreapi.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bookstoreapi.BookListMethod;
import com.example.bookstoreapi.GlobalMethod;
import com.example.bookstoreapi.controller.request.OrderRequest;
import com.example.bookstoreapi.controller.response.PriceResponse;
import com.example.bookstoreapi.exception.BookIdNotFoundException;
import com.example.bookstoreapi.exception.BookQuantityException;
import com.example.bookstoreapi.exception.OrderNotFoundException;
import com.example.bookstoreapi.exception.OrderRequestException;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.model.Orders;
import com.example.bookstoreapi.repository.OrdersRepository;

@Service
public class OrdersServiceImplement implements OrdersService {

	@Autowired
	private RestTemplate restTemplete;

	private BookListMethod bookListMethod;
	private GlobalMethod globalMethod;
	private OrdersRepository ordersRepository;

	public OrdersServiceImplement(BookListMethod bookListMethod, GlobalMethod globalMethod,
			OrdersRepository ordersRepository) {
		super();
		this.bookListMethod = bookListMethod;
		this.globalMethod = globalMethod;
		this.ordersRepository = ordersRepository;
	}

	@Override
	public PriceResponse saveOrder(OrderRequest orderRequest, String jwtToken) {

		List<LinkedHashMap> allBooks = restTemplete.getForObject("https://scb-test-book-publisher.herokuapp.com/books",
				List.class);
		ArrayList<Book> list_book = bookListMethod.createBookList(allBooks);
		Orders orders = new Orders();
		PriceResponse priceResponse = new PriceResponse();
		int book_id = 0;
		int book_order_quantity = 0;
		try {
			book_id = (int) orderRequest.getOrders().get(1);
			book_order_quantity = (int) orderRequest.getOrders().get(0);
		} catch (Exception e) {
			throw new OrderRequestException();
		}
		if (book_order_quantity < 0) {
			throw new BookQuantityException(book_order_quantity);
		}
		double sum_price = calBookPrice(orderRequest, list_book);
		if (sum_price == -99) {
			throw new BookIdNotFoundException(book_id);
		}
		priceResponse.setPrice(sum_price);
		String token = globalMethod.substringAfter(jwtToken, " ");
		String token_id = globalMethod.decodeJWT(token).getId();
		int user_id = Integer.parseInt(token_id);
		orders.setBook_id(book_id);
		orders.setBook_quantity((int) orderRequest.getOrders().get(0));
		orders.setTotal_price(sum_price);
		orders.setUser_id(user_id);
		ordersRepository.save(orders);
		return priceResponse;

	}

	public static double calBookPrice(OrderRequest orderRequest, ArrayList<Book> list_book) {
		double sum_price = 0;
		int book_id = (int) orderRequest.getOrders().get(1);
		int book_order_quantity = (int) orderRequest.getOrders().get(0);
		for (Book book : list_book) {
			if (book.getId() == book_id) {
				sum_price = (int) (book.getPrice() * book_order_quantity) * 1.07;
				break;
			} else {
				// return -99 when not found book_id
				sum_price = -99;
			}
		}

		return sum_price;
	}

	@Override
	public void deleteOrderByUserId(String jwtToken) {
		String token = globalMethod.substringAfter(jwtToken, " ");
		String token_id = globalMethod.decodeJWT(token).getId();
		long user_id = Integer.parseInt(token_id);
		try {
			ordersRepository.deleteOrderByUserId(user_id);

		} catch (Exception ex) {
			throw new OrderNotFoundException(user_id);
		}

	}

}
