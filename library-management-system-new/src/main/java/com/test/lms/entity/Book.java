package com.test.lms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String book_code;
	private String book_name;
	private String book_author;
	private LocalDate book_dateAdded = LocalDate.now();
	

	public Book(String book_code, String book_name, String book_author, LocalDate book_dateAdded) {
		super();
		this.book_code = book_code;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_dateAdded = book_dateAdded;
	}

	public Book() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBook_code() {
		return book_code;
	}
	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public LocalDate getBook_dateAdded() {
		return book_dateAdded;
	}

	public void setBook_dateAdded(LocalDate book_dateAdded) {
		this.book_dateAdded = book_dateAdded;
	}
	
}
