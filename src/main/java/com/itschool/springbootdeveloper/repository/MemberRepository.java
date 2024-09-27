package com.itschool.springbootdeveloper.repository;

import com.itschool.springbootdeveloper.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Persistence 계층; 영속성 계층 : 모든 데이터베이스 관련 로직 처리(CRUD)
// Repository가 영속성 계층의 역할을 수행함
// Service 계층 <-> Persistence 계층
// 데이터베이스에 접근하는 DAO 객체를 사용할 수도 있음
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 기본 메서드 종류
    // save(S entity): 데이터 저장, id를 기준으로 데이터가 없는 경우 새롭게 생성, 존재하면 기존 데이터 업데이트
    // findAll() : 모든 데이터 조회
    // deleteAll() : 테이블의 모든 데이터를 삭제

    // 메서드 생성 규칙
    // findBy~ : 조회 시 사용하며, findBy로 시작하고, 그 뒤에 조건이 온다
    // 예시) findById, findByName, findByIdAndName
    // 조건 연산자 : And, Or, Like, NotLike, GreaterThan, LessThan, IsNull, IsNotNull
    // Between, In, NotIn...

    // Optional<T> 클래스를 사용해 NPE를 방지
    Optional<Member> findById(Long id);
    
    // findByName 추상 메소드 정의
    Optional<List<Member>> findByName(String name);
}
