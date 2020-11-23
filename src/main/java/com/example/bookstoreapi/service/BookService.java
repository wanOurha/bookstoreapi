package com.example.bookstoreapi.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.example.bookstoreapi.controller.response.BookListResponse;

public interface BookService {
	BookListResponse getRecommendBooksList(List<LinkedHashMap> allBooks, List<LinkedHashMap> recommendBooks);
}
