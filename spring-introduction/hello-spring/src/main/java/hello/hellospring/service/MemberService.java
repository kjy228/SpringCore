package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/* 비지니스 로직 구현 */
//@Service
public class MemberService {
/*     인터페이스를 new로 생성할 수 없으니 항상 구현 인스턴스가 들어간다
     final 키워드는 값을 생성자에서 초기화 한 이후에 변경할 수 X*/
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //위처럼 사용하면 test에서 사용하는 repository와 다른 repository를 사용하게 된다.
    private final MemberRepository memberRepository;

    //memberRepository를 외부(test)에서 넣어준다. = DI
    //따라서 각각의 테스트 시 memberRepository를 공유할 수 있다
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원 가입 */
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //Ctrl + Alt + Shift + T -> 리팩토링 관련 전체 항목을 조회 <Extract Method>
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //(Optional 제공) ifPresent : null이 아니라 값이 있다면
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}