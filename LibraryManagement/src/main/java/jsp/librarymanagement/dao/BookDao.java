package jsp.librarymanagement.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.librarymanagement.entity.Book;
import jsp.librarymanagement.repository.BookRepository;

@Repository
public class BookDao {
	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookById(int id) {
		return bookRepository.findById(id);
	}

	public List<Book> getBooksByGenre(String genre) {
		return bookRepository.findByGenre(genre);
	}

	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	public Optional<Book> deleteBookById(int id) {
		return bookRepository.findById(id);
	}

	public void delete(Book book) {
		bookRepository.delete(book);
	}
}
