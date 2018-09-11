package com.capgemini.mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BookDAOTest {

	private static BookDAO mockedBookDAO;
	private static Book book1;
	private static Book book2;

	@Before
	public void setUp() {
        mockedBookDAO = mock(BookDAO.class);

		book1 = new Book("8131721019", "Compilers Principles", Arrays.asList(
				"D. Jeffrey Ulman", "Ravi Sethi", "Alfred V. Aho",
				"Monica S. Lam"), "Pearson Education Singapore Pte Ltd", 2008,
				1009, "BOOK_IMAGE");

		book2 = new Book("9788183331630", "Let Us C 13th Edition",
				Arrays.asList("Yashavant Kanetkar"), "BPB PUBLICATIONS", 2012,
				675, "BOOK_IMAGE");

        when(mockedBookDAO.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
		when(mockedBookDAO.addBook(book1)).thenReturn(book1.getIsbn());
        when(mockedBookDAO.updateBook(book1)).thenReturn(book1.getIsbn());
        when(mockedBookDAO.getBook("8131721019")).thenReturn(book1);
	}

	@Test
	public void testGetAllBooks() throws Exception {
		List<Book> allBooks = mockedBookDAO.getAllBooks();
		assertThat(allBooks).hasSize(2);
		Book myBook = allBooks.get(0);
		assertThat(myBook.getIsbn()).isEqualTo("8131721019");
		assertThat(myBook.getTitle()).isEqualTo("Compilers Principles");
		assertThat(myBook.getAuthors()).hasSize(4);
		assertThat(myBook.getYearOfPublication()).isEqualTo(2008);
		assertThat(myBook.getNumberOfPages()).isEqualTo(1009);
		assertThat(myBook.getPublication()).isEqualTo(
				"Pearson Education Singapore Pte Ltd");
		assertThat(myBook.getImage()).isEqualTo("BOOK_IMAGE");
	}

	@Test
	public void testGetBook() {
		String isbn = "8131721019";

		Book myBook = mockedBookDAO.getBook(isbn);

		assertThat(myBook).isNotNull();
		assertThat(myBook.getIsbn()).isEqualTo(isbn);
		assertThat(myBook.getTitle()).isEqualTo("Compilers Principles");
		assertThat(myBook.getAuthors()).hasSize(4);
		assertThat(myBook.getYearOfPublication()).isEqualTo(2008);
		assertThat(myBook.getNumberOfPages()).isEqualTo(1009);
		assertThat(myBook.getPublication()).isEqualTo(
				"Pearson Education Singapore Pte Ltd");
	}

	@Test
	public void testAddBook() {
		String isbn = mockedBookDAO.addBook(book1);
		assertThat(isbn).isNotNull().isEqualTo(book1.getIsbn());
	}

	@Test
	public void testUpdateBook() {
		String isbn = mockedBookDAO.updateBook(book1);
		assertThat(isbn).isNotNull().isEqualTo(book1.getIsbn());
	}
}
