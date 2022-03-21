package com.test.lms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.test.lms.entity.Author;
import com.test.lms.entity.Book;
import com.test.lms.helper.Message;
import com.test.lms.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private RestTemplate restTemplate;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public ModelAndView listBooks(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("books", bookService.getAllBooks());
		modelAndView.setViewName("books.html");
		return modelAndView;
	}

	@GetMapping("/books/new")
	public ModelAndView createBookForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		String url = "http://localhost:9092/author";
		List<Author> authors = restTemplate.getForObject(url, List.class);
		List<String> auth = new ArrayList<>();
		for(Object obj : authors) {
			String str = obj.toString();
			String name = obj.toString().substring(11, str.length()-1);
			auth.add(name);
		}

//		System.out.println(authors);

		// create book object to hold book from data
		Book book = new Book();
		model.addAttribute("book", book);

		model.addAttribute("authorList", auth);

		modelAndView.setViewName("create_book.html");
		return modelAndView;
	}

	@PostMapping("/books")
	public ModelAndView saveBook(@ModelAttribute("books") Book book,HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView();
			bookService.saveBook(book);
			session.setAttribute("message",new Message("Book Added Successfully !!", "alert-success"));
			modelAndView = new ModelAndView("redirect:books");
			return modelAndView;
		}catch(Exception e) {
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("message",new Message("Book Code Already in use, Kindly use another book code !!", "alert-danger"));
			modelAndView = new ModelAndView("redirect:books");
			return modelAndView;
		}
		
	}

	@GetMapping("/books/edit/{id}")
	public ModelAndView editBook(@PathVariable Long id, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("book", bookService.getBookById(id));
		modelAndView.setViewName("edit_book.html");
		
		String url = "http://localhost:9092/author";
		List<Author> authors = restTemplate.getForObject(url, List.class);
		
		List<String> auth = new ArrayList<>();
		for(Object obj : authors) {
			String str = obj.toString();
			String name = obj.toString().substring(11, str.length()-1);
			auth.add(name);
		}
		
		model.addAttribute("authorList", auth);
		return modelAndView;
	}

	@PostMapping("/books/{id}")
	public ModelAndView updateBook(@PathVariable Long id, @ModelAttribute("book") Book book, Model model,HttpSession session) {
		try {
			// get book from database by id.
			Book existingBook = bookService.getBookById(id);
			existingBook.setId(id);
			existingBook.setBook_code(book.getBook_code());
			existingBook.setBook_name(book.getBook_name());
			existingBook.setBook_author(book.getBook_author());
			existingBook.setBook_dateAdded(book.getBook_dateAdded());

			// save updated book object
			bookService.updateBook(existingBook);
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("message",new Message("Book Updated Successfully !!", "alert-success"));
			modelAndView = new ModelAndView("redirect:/books");
			return modelAndView;
		}catch(Exception e) {
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("message",new Message("Book Code Already in use, Kindly use another book code !!", "alert-danger"));
			modelAndView = new ModelAndView("redirect:/books");
			return modelAndView;
		}
		
	}

	// To handle delete book request

	@GetMapping("/books/{id}")
	public ModelAndView deleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView("redirect:/books");
		return modelAndView;
	}
}
