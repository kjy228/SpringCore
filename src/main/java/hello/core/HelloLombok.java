package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("삼코");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        // @ToString를 사용 시 문자열로 출력해준다
        // @ToString를 사용하지 않을 경우 패키지경로가 출력된다
        System.out.println("helloLombok = " + helloLombok);
    }
}
