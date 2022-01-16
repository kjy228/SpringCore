package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Ctrl + Shift + T -> 자동으로 Test 생성
class MemberServiceTest {
//    MemberService memberService = new MemberService();
    MemberService memberService;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;
/*    cf.'구현체 이름 = new 구현체'로 사용한 이유
    MemberRepository로 가져오면 clearStore()에서 오류 발생
    clearStore()는 인터페이스(MemberRepository)에 정의되어 있지 않기 때문이다*/

    @BeforeEach
    public void beforeEach(){
        //각각의 테스트 실행 전 memberRepository를 생성해서 MemberService에 넣어준다
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 무언가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when : 이거를 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과는 이렇게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }


    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}