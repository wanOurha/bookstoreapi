package com.example.bookstoreapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.model.RecommendBooks;

@Configuration
@Component
public class BookListMethod {

	public BookListMethod() {
		super();
	}

	public static ArrayList<Book> createBookList(List<LinkedHashMap> allBooks) {
		ArrayList<Book> array_list_book = new ArrayList();
		for (LinkedHashMap allBook : allBooks) {
			Book book = new Book();
			book.setAuthor(allBook.get("author_name").toString());
			book.setId(Integer.parseInt(allBook.get("id").toString()));
			book.setName(allBook.get("book_name").toString());
			book.setPrice(Double.parseDouble(allBook.get("price").toString()));
			array_list_book.add(book);
		}
		return array_list_book;

	}

	public static ArrayList<Book> sortBook(ArrayList<Book> allBooks) {
		ArrayList<Book> sorted_book = new ArrayList();
		HashMap<String, String> book_sort_by_name = new HashMap<String, String>();
		int book_count = 0;
		for (Book allBook : allBooks) {
			book_count++;
			book_sort_by_name.put("book " + book_count, allBook.getName());
		}

		Set<Entry<String, String>> entries = book_sort_by_name.entrySet();

		Comparator<Entry<String, String>> valueComparator = new Comparator<Entry<String, String>>() {
			@Override
			public int compare(Entry<String, String> e1, Entry<String, String> e2) {
				String v1 = e1.getValue();
				String v2 = e2.getValue();
				return v1.compareTo(v2);
			}
		};
		List<Entry<String, String>> listOfEntries = new ArrayList<Entry<String, String>>(entries);
		Collections.sort(listOfEntries, valueComparator);
		LinkedHashMap<String, String> sortedByValue = new LinkedHashMap<String, String>(listOfEntries.size());
		for (Entry<String, String> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}
		Set<Entry<String, String>> entrySetSortedByValue = sortedByValue.entrySet();
		for (Entry<String, String> all_sort_book : entrySetSortedByValue) {
			for (Book allBook : allBooks) {
				if (all_sort_book.getValue().equals(allBook.getName())) {
					Book book = new Book();
					book.setAuthor(allBook.getAuthor());
					book.setId(allBook.getId());
					book.setName(allBook.getName());
					book.setPrice(allBook.getPrice());
					sorted_book.add(book);
				}
			}
		}
		return sorted_book;

	}

	public static ArrayList<RecommendBooks> recommendBooksEdit(ArrayList<Book> allBooks,
			ArrayList<Book> recommendBooks) {
		ArrayList<Book> book = new ArrayList();
		book = allBooks;
		ArrayList<RecommendBooks> book_list = new ArrayList();
		for (Book recommendBook : recommendBooks) {
			for (Book allBook : allBooks) {
				if (allBook.getName().equals(recommendBook.getName())) {
					RecommendBooks addRecommendBook = new RecommendBooks();
					addRecommendBook.setIs_recommended(true);
					addRecommendBook.setAuthor(allBook.getAuthor());
					addRecommendBook.setId(allBook.getId());
					addRecommendBook.setName(allBook.getName());
					addRecommendBook.setPrice(allBook.getPrice());
					book_list.add(addRecommendBook);
					book.remove(allBook);
					break;
				} else {
					continue;
				}
			}
		}
		for (Book not_recommendBook : book) {
			RecommendBooks addNo_RecommendBook = new RecommendBooks();
			addNo_RecommendBook.setIs_recommended(false);
			addNo_RecommendBook.setAuthor(not_recommendBook.getAuthor());
			addNo_RecommendBook.setId(not_recommendBook.getId());
			addNo_RecommendBook.setName(not_recommendBook.getName());
			addNo_RecommendBook.setPrice(not_recommendBook.getPrice());
			book_list.add(addNo_RecommendBook);
		}
		return book_list;
	}

}
