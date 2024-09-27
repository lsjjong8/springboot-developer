package com.itschool.springbootdeveloper.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // (지연 로딩) 프록시 조회에서 접근을 위해 protected 또는 public
// @AllArgsConstructor
@Getter
// 엔티티는 데이터베이스의 테이블과 매핑되는 객체를 의미함
// 엔티티는 본질적으로는 자바 객체이므로 일반 객체와 다르지 않음
// 데이터베이스의 테이블과 직접 연결된다는 특별한 특징이 있음
// 엔티티 매니저 : 엔티티 매니저는 엔티티를 관리해 데이터베이스와 어플리케이션 사이에서 객체를 생성, 수정, 삭제하는 등의 역할을 함
// 엔티티 매니저는 Spring Data JPA에서 관리하므로 직접 생성하거나 관리할 필요가 없음
// 엔티티 매니저 팩토리 : 엔티티 매니저를 생성, 어플리케이션 내에서 단 하나만 존재
@Entity/*(name = "users")*/ // Member 객체를 JPA가 관리하는 엔티티로 지정, 속성 중에 name 값을 사용하면 name의 값을 가진 테이블 이름과 매핑이 됨
public class Member {
    @Id // id 필드를 기본키로 지정, (Long타입의 id 필드를 기본키로 우리가 지정)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임(= AUTO_INCREMENT)
    // AUTO : 선택한 데이터베이스 방언(dialect)에 따라 방식을 자동으로 선택(기본값), postgresql auto하게되면 sequence 방식을 사용
    // SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본키를 할당하는 방법, 오라클에서 주로 사용
    // TABLE : 키 생성 테이블 사용
    @Column(name = "id", updatable = false) // DB 실제 컬럼명, name을 넣지 않으면 변수명을 lower_snake_case로 간주
    private Long id; // DB 테이블의 'id' 컬럼과 매칭

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT ''") // name이라는 not column 과 매핑
    // name : 필드와 매핑할 컬럼이름, 설정하지 않으면 필드 이름으로 지정해줌
    // nullable : 컬럼의 null 허용 여부, 설정하지 않으면 true(nullable), false(NOT NULL)
    // unique : 컬럼의 유일한 값(unique 제약조건) 여부, 설정하지 않으면 false(non unique)
    // columnDefinition : 컬럼 정보 설정, DDL을 직접 정의하고 싶을때
    private String name; // DB테이블의 'name' 컬럼과 매칭

    public Member(String name) {
        this.name = name;
    }
}
