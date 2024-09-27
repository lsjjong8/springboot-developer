package com.itschool.springbootdeveloper.service;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service 계층 : 비즈니스 로직 처리와 비즈니스 관련된 도메인 모델의 적합성 검증
// 트랜잭션 관리 및 처리
@Service
public class MemberService {

    // Service 계층 <-> Persistence 계층
    @Autowired // 빈 주입
    MemberRepository memberRepository;

    public List<Member> getAllMember() {
        return memberRepository.findAll(); // 멤버 목록 얻기
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).get();
    }

    public List<Member> searchMembersByName(String name) {
        return memberRepository.findByName(name).get();
    }

    public void test() {
        // JPA, Hibernate 개발자가 직접 쓰게되면 엔티티를 직접 관리하고 커밋해야 하는 등 신경쓸 부분이 많음
        // 스프링 데이터는 비즈니스 로직에 더 집중할 수 있게 데이터베이스 사용 기능을 클래스 레벨에서 추상화함
        // 해당 인터페이스에는 CRUD 포함한 여러 메서드가 포함돼 있으며, 알아서 쿼리를 만들어줌

        // 1. 생성 (Create)
        // IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행
        // 트랜잭션이 바로 커밋
        Member member = new Member("A");
        memberRepository.save(member); // 기존에 존재하는 확인, 없으면 CREATE, 있으면 UPDATE. 바로 쿼리가 날아감

        // 2. 조회 (Read)
        Optional<Member> member2 = memberRepository.findById(1L); // 단건 조회
        List<Member> allMember = memberRepository.findAll(); // 전체 조회

        // 3. 삭제 (Delete)
        memberRepository.deleteById(1L);
    }
}
