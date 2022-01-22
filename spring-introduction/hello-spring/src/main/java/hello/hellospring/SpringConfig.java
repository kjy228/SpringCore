package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ▶▶ 2.자바 코드로 직접 스프링 빈 등록하기
//@Configuration도 @Component가 정의되어있으므로 컴포넌트 스캔 대상임
@Configuration
public class SpringConfig {
    //Jdbc 사용 시
//    @Autowired DataSource dataSource;
    //JPA 사용 시
//    @PersistenceContext
//    private EntityManager em;
    //스프링 데이터 JPA 사용 시
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //스프링 빈에 등록된 MemoryMemberRepository를 넣어준다 (@Autowired 역할)
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

    //물론 @Component 사용도 가능하나 AOP는 스프링 빈에 직접 등록해주는게 좋다
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}