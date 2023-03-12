package org.tutorial;

import java.util.List;

public class BookDAOMockImpl implements BookDAO {

	@Override
	public List<Book> findByAll() {
		Book book1 = new Book(1, "titre1", "author1");
		Book book2 = new Book(2, "Title2", "Author2");
		List<Book> books = List.of(book1, book2);
		return books;
	}

	@Override
	public List<Book> findByTitle(String searchText) {
		Book book1 = new Book(1, "titre1", "author1");
		List<Book> books = List.of(book1);
		return books;
	}

}
