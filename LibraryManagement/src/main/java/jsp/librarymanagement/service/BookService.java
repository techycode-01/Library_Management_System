package jsp.librarymanagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import jsp.librarymanagement.dao.AuthorDao;
import jsp.librarymanagement.dao.BookDao;
import jsp.librarymanagement.dto.ResponseStructure;
import jsp.librarymanagement.entity.Author;
import jsp.librarymanagement.entity.Book;
import jsp.librarymanagement.exception.IdNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;

	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
		Optional<Author> author = authorDao.getAuthorById(book.getAuthor().getId());
		if (author.isPresent()) {
			Book receivedBook = bookDao.saveBook(book);
			book.setAuthor(author.get());
			ResponseStructure<Book> structure = new ResponseStructure<Book>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("success");
			structure.setData(receivedBook);
			return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		List<Book> books = bookDao.getAllBooks();
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(books);
		return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Book>> getBookById(int id) {
		Optional<Book> opt = bookDao.getBookById(id);
		ResponseStructure<Book> structure = new ResponseStructure<Book>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
		List<Book> books = bookDao.getBooksByGenre(genre);

		if (books.isEmpty()) {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.NOT_FOUND);
		} else {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("success");
			structure.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);

		}
	}

	public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
		Book receivedBook = bookDao.updateBook(book);
		ResponseStructure<Book> structure = new ResponseStructure<Book>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(receivedBook);
		return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Book>> deleteBookById(int id) {
		Optional<Book> opt = bookDao.deleteBookById(id);
		ResponseStructure<Book> structure = new ResponseStructure<Book>();
		if (opt.isPresent()) {
			Book book = opt.get();
			bookDao.delete(book);
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.NOT_FOUND);

		}
	}

}
