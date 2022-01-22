package hello.hellospring.domain;

import javax.persistence.*;

//아 이거는 JPA가 관리하는 엔티티군
@Entity
@Table(name = "MEMBEREXAM") //테이블 매칭
public class Member {
    //IDENTITY -> DB가 알아서 시퀀스를 생성해줌
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")  //컬럼 매칭
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}