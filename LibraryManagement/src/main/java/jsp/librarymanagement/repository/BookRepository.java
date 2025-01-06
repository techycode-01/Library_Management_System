package jsp.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.librarymanagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByGenre(String genre);
}
