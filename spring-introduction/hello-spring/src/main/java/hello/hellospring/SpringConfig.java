package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ▶▶ 2.자바 코드로 직접 스프링 빈 등록하기
//@Configuration도 @Component가 정의되어있으므로 컴포넌트 스캔 대상임
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        //스프링 빈에 등록된 MemoryMemberRepository를 넣어준다 (@Autowired 역할)
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}