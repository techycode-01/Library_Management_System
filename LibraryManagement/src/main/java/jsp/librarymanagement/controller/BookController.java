package jsp.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.librarymanagement.dto.ResponseStructure;
import jsp.librarymanagement.entity.Book;
import jsp.librarymanagement.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
    
	// Insert
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveAuthor(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	// Get/Fetch all the records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		return bookService.getAllBooks();
	}
    
	// Get/Fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	// Get/Fetch record by Genre
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
		return bookService.getBooksByGenre(genre);
	}

	// update record by Id
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	// delete record by Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> deleteBookById(@PathVariable int id) {
		return bookService.deleteBookById(id);
	}

}
