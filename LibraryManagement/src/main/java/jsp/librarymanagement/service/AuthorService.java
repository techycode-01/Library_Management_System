package jsp.librarymanagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.librarymanagement.dao.AuthorDao;
import jsp.librarymanagement.dto.ResponseStructure;
import jsp.librarymanagement.entity.Author;

@Service
public class AuthorService {
	@Autowired
	private AuthorDao authorDao;

	public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author author) {
		Author receivedAuthor = authorDao.saveAuthor(author);
		ResponseStructure<Author> structure = new ResponseStructure<Author>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("success");
		structure.setData(receivedAuthor);
		return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthors() {
		List<Author> authors = authorDao.getAllAuthors();
		ResponseStructure<List<Author>> structure = new ResponseStructure<List<Author>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(authors);
		return new ResponseEntity<ResponseStructure<List<Author>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Author>> getAuthorById(int id) {
		Optional<Author> opt = authorDao.getAuthorById(id);
		ResponseStructure<Author> structure = new ResponseStructure<Author>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Author>> updateAuthor(Author author) {
		Author receivedAuthor = authorDao.updateAuthor(author);
		ResponseStructure<Author> structure = new ResponseStructure<Author>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(receivedAuthor);
		return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Author>> deleteAuthorById(int id) {
		Optional<Author> opt = authorDao.deleteAuthorById(id);
		ResponseStructure<Author> structure = new ResponseStructure<Author>();
		if (opt.isPresent()) {
			Author author = opt.get();
			authorDao.delete(author);
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.NOT_FOUND);

		}
	}
}
