package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //반환값으로 null 을 return 받는 경우, 추후 null을 반환해준 라인과는 상관없는 코드에서 null pointer Exception이 터질 수 있어서 트래킹이 어렵다
    //따라서 null일 수도 있는 상황에 null을 상자에 담은 Optional 자체를 반환하기 위한 목적
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}