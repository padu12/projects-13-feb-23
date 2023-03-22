package ru.alishev.springcourse.models;

public class Book {
	private int book_id;
	private int person_id;
	private String name;
	private String author;
	private int year;

	public Book(String name, String author, int year) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
	}

	public Book() {
		super();
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
