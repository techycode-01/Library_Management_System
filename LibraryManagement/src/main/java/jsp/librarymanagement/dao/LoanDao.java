package jsp.librarymanagement.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import jsp.librarymanagement.entity.Loan;
import jsp.librarymanagement.repository.LoanRepository;

@Repository
public class LoanDao {
	@Autowired
	private LoanRepository loanRepository;

	public Loan saveLoan(Loan loan) {
		return loanRepository.save(loan);
	}

	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	public Optional<Loan> getLoanById(int id) {
		return loanRepository.findById(id);
	}

	public Loan updateLoan(Loan loan) {
		return loanRepository.save(loan);
	}

	public Optional<Loan> deleteLoanById(int id) {
		return loanRepository.findById(id);
	}

	public void delete(Loan loan) {
		loanRepository.delete(loan);
	}
}
