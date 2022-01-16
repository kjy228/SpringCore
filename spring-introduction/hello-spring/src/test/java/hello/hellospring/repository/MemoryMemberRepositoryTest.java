package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 순서를 보장하지 않는다.
    //따라서 하나의 테스트가 끝날 때 저장소나 공용 데이터를 깔끔하게 clear해줘야 한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("만세");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //1. 일반 자바 문법으로 비교
//        System.out.println("result = " + (result == member));

        //2. (junit) Expected 값과 Actual 값을 비교
//        Assertions.assertEquals(result, member);

        //3. (assertj) Expected 값과 Actual 값을 비교
        //Alt + Enter -> static import하여 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("대한");
        repository.save(member1);

        //shift + F6 -> Rename 한꺼번에 가능
        Member member2 = new Member();
        member2.setName("민국");
        repository.save(member2);

        Member result = repository.findByName("대한").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("대한");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("민국");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}