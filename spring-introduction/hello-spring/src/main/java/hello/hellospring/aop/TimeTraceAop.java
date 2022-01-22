package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//AOP: Aspect Oriented Programming
//공통 관심 사항과 핵심 관심 사항을 분리시키자!!
@Aspect
public class TimeTraceAop {
    //패키지 하위 클래스들에 다 적용할거다
//    @Around("execution(* hello.hellospring..*(..))")
    //하지만 스프링 빈을 직접 참조 시 SpringConfig의 timeTraceAop()도 AOP로 처리되므로 빈 순환 참조 에러 발생
    // 그럴 경우엔!!아래와 같이 AOP대상에서 SpringConfig를 빼준다.
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START ▶ " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END ▶ " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
