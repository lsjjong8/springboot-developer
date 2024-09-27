package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Presentation 계층 : 웹 클라이언트의 요청 및 응답을 처리
// RESTController(REST API, json) 또는 Controller는 라우터 역할을 함
// 라우터는 HTTP 요청과 메서드를 연결하는 장치
// @RestController 애너테이션은 @Component 를 포함
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    // Presentation 계층 <-> Service 계층
    @Autowired
    MemberService memberService;

    // Controller를 라우터로 지정해 GET 요청이 오면 메서드를 실행
    @GetMapping("") // /api/member
    public List<Member> getAllMembers() {
        // Presentation 계층은 Service 계층과 관련있음
        List<Member> members = memberService.getAllMember();
        return members;
    }

    @GetMapping("{id}") // /api/member/{id}
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("searchByName/{name}") // /api/member/{name}
    public List<Member> searchMembersByName(@PathVariable String name) {
        return memberService.searchMembersByName(name);
    }

    // GetMapping으로 메소드 추가
    // id와 name 둘 다 받아서 데이터를 return
    // repository : findByIdAndName()
    @GetMapping("{id}/{name}")
    public String getMemberByIdAndName(@PathVariable int id,
                                       @PathVariable String name) {
        return "테스트 : " + id + name;
    }

    @GetMapping("test")
    public String test() {
        memberService.test();

        return "test-api";
    }
}
