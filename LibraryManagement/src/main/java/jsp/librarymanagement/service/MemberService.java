package jsp.librarymanagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.librarymanagement.dao.MemberDao;
import jsp.librarymanagement.dto.ResponseStructure;
import jsp.librarymanagement.entity.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public ResponseEntity<ResponseStructure<Member>> saveMember(Member member) {
		Member receivedMember = memberDao.saveMember(member);
		ResponseStructure<Member> structure = new ResponseStructure<Member>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("success");
		structure.setData(receivedMember);
		return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers() {
		List<Member> members = memberDao.getAllMembers();
		ResponseStructure<List<Member>> structure = new ResponseStructure<List<Member>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(members);
		return new ResponseEntity<ResponseStructure<List<Member>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Member>> getMemberById(int id) {
		Optional<Member> opt = memberDao.getMemberById(id);
		ResponseStructure<Member> structure = new ResponseStructure<Member>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Member>> updateMember(Member member) {
		Member receivedMember = memberDao.updateMember(member);
		ResponseStructure<Member> structure = new ResponseStructure<Member>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(receivedMember);
		return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Member>> deleteMemberById(int id) {
		Optional<Member> opt = memberDao.deleteMemberById(id);
		ResponseStructure<Member> structure = new ResponseStructure<Member>();
		if (opt.isPresent()) {
			Member member = opt.get();
			memberDao.delete(member);
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.NOT_FOUND);

		}
	}
}
