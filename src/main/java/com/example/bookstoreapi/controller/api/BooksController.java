package com.example.bookstoreapi.controller.api;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bookstoreapi.BookListMethod;
import com.example.bookstoreapi.controller.response.BookListResponse;
import com.example.bookstoreapi.service.BookServiceImplement;

@RestController
public class BooksController {

	@Autowired
	private RestTemplate restTemplete;

	private BookServiceImplement bookServiceImplement;

	public BooksController(BookListMethod bookListMethod, BookServiceImplement bookServiceImplement) {
		super();
		this.bookServiceImplement = bookServiceImplement;
	}

	@RequestMapping("/books")
	public BookListResponse getBook() {
		List<LinkedHashMap> allBooks = restTemplete.getForObject("https://scb-test-book-publisher.herokuapp.com/books",
				List.class);
		List<LinkedHashMap> recommendBooks = restTemplete
				.getForObject("https://scb-test-book-publisher.herokuapp.com/books/recommendation", List.class);
		return bookServiceImplement.getRecommendBooksList(allBooks, recommendBooks);
	}

}
