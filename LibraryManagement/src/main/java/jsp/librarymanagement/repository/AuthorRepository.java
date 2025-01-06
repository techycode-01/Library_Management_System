package jsp.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.librarymanagement.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
