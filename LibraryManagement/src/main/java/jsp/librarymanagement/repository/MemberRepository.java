package jsp.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.librarymanagement.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
