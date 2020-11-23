package com.example.bookstoreapi.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstoreapi.BookListMethod;
import com.example.bookstoreapi.controller.response.BookListResponse;
import com.example.bookstoreapi.model.Book;

@Service
public class BookServiceImplement implements BookService {

	private BookListMethod bookListMethod;

	public BookServiceImplement(BookListMethod bookListMethod) {
		this.bookListMethod = bookListMethod;
	}

	@Override
	public BookListResponse getRecommendBooksList(List<LinkedHashMap> allBooks, List<LinkedHashMap> recommendBooks) {
		BookListResponse booklistResponse = new BookListResponse();
		ArrayList<Book> list_book = bookListMethod.createBookList(allBooks);
		ArrayList<Book> list_recommend_book = bookListMethod.createBookList(recommendBooks);
		ArrayList<Book> sorted_list_book = bookListMethod.sortBook(list_book);
		ArrayList<Book> sorted_list_recommend_book = bookListMethod.sortBook(list_recommend_book);
		booklistResponse.setBooks(bookListMethod.recommendBooksEdit(sorted_list_book, sorted_list_recommend_book));
		return booklistResponse;
	}

}
