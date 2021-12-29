package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정
        basePackages = "hello.core.member",
        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        basePackageClasses = AutoAppConfig.class,
        // 이 전에 만들어놓은 AppConfig에 @Configuration가 붙어있기 때문에
        // 자동으로 빈을 지정해준다. 따라서 필터를 지정해줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
)
public class AutoAppConfig {

}
