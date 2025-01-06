package jsp.librarymanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.librarymanagement.entity.Author;
import jsp.librarymanagement.repository.AuthorRepository;

@Repository
public class AuthorDao {
	@Autowired
	private AuthorRepository authorRepository;

	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	public Optional<Author> getAuthorById(int id) {
		return authorRepository.findById(id);
	}

	public Author updateAuthor(Author author) {
		return authorRepository.save(author);
	}

	public Optional<Author> deleteAuthorById(int id) {
		return authorRepository.findById(id);
	}

	public void delete(Author author) {
		authorRepository.delete(author);
	}
}
