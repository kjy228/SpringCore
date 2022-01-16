package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// ▶▶ 1. 컴포넌트 스캔을 이용하여 자동 의존관계 설정
/*스프링 컨테이너는 @Component가 정의된 모든 클래스들을 스캔하여 스프링 빈으로 등록한다
(@Controller 선언부에 @Component가 정의되어 있음)*/
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    //컨트롤러와 서비스를 연결시켜주기 위해(DI = 의존관계 주입) @Autowired를 선언
    //스프링 빈이 스프링 컨테이너에 등록된 memberService를 연결시켜준다
    //MemberService에도 @Service를, MemoryMemberRepository에도 @Repository를 정의해줘야 한다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Get방식은 어떠한 정보를 가져와서 조회하기 위해 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //Post방식은 데이터를 서버로 제출하여 등록 또는 수정하기 위해 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //회원가입이 끝난 후 홈 화면으로 이동
        return "redirect:/";
    }

    //Get방식은 어떠한 정보를 가져와서 조회하기 위해 사용
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}