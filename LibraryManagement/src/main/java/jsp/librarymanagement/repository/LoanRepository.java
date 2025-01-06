package jsp.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.librarymanagement.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
