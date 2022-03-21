package com.test.lms.service;

import java.util.List;
import com.test.lms.entity.Book;

public interface BookService{
	List<Book> getAllBooks();
	Book saveBook(Book book);
	Book updateBook(Book book);
	Book getBookById(Long id);
	void deleteBookById(Long id);
}
