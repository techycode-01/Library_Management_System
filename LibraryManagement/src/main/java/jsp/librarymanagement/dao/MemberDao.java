package jsp.librarymanagement.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.librarymanagement.entity.Member;
import jsp.librarymanagement.repository.MemberRepository;

@Repository
public class MemberDao {
	@Autowired
	private MemberRepository memberRepository;

	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}

	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> getMemberById(int id) {
		return memberRepository.findById(id);
	}

	public Member updateMember(Member member) {
		return memberRepository.save(member);
	}

	public Optional<Member> deleteMemberById(int id) {
		return memberRepository.findById(id);
	}

	public void delete(Member member) {
		memberRepository.delete(member);
	}
}
