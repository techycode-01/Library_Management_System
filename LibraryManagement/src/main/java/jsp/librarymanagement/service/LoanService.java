package jsp.librarymanagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.librarymanagement.dao.BookDao;
import jsp.librarymanagement.dao.LoanDao;
import jsp.librarymanagement.dao.MemberDao;
import jsp.librarymanagement.dto.ResponseStructure;
import jsp.librarymanagement.entity.Book;
import jsp.librarymanagement.entity.Loan;
import jsp.librarymanagement.entity.Member;
import jsp.librarymanagement.exception.IdNotFoundException;

@Service
public class LoanService {
	@Autowired
	private LoanDao loanDao;

	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;

	public ResponseEntity<ResponseStructure<Loan>> saveLoan(Loan loan) {
		Optional<Book> book = bookDao.getBookById(loan.getBook().getId());
		Optional<Member> member = memberDao.getMemberById(loan.getMember().getId());
		if (book.isPresent()) {
			Loan receivedLoan = loanDao.saveLoan(loan);
			loan.setBook(book.get());
			ResponseStructure<Loan> structure = new ResponseStructure<Loan>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("success");
			structure.setData(receivedLoan);
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.CREATED);
		}
		if (member.isPresent()) {
			Loan receivedLoan = loanDao.saveLoan(loan);
			loan.setMember(member.get());
			ResponseStructure<Loan> structure = new ResponseStructure<Loan>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("success");
			structure.setData(receivedLoan);
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
		List<Loan> loans = loanDao.getAllLoans();
		ResponseStructure<List<Loan>> structure = new ResponseStructure<List<Loan>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(loans);
		return new ResponseEntity<ResponseStructure<List<Loan>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Loan>> getLoanById(int id) {
		Optional<Loan> opt = loanDao.getLoanById(id);
		ResponseStructure<Loan> structure = new ResponseStructure<Loan>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Loan>> updateLoan(Loan loan) {
		Loan receivedLoan = loanDao.updateLoan(loan);
		ResponseStructure<Loan> structure = new ResponseStructure<Loan>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(receivedLoan);
		return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Loan>> deleteLoanById(int id) {
		Optional<Loan> opt = loanDao.deleteLoanById(id);
		ResponseStructure<Loan> structure = new ResponseStructure<Loan>();
		if (opt.isPresent()) {
			Loan loan = opt.get();
			loanDao.delete(loan);
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);

		}
	}
}
