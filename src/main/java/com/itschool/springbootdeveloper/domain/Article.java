package com.itschool.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false) // not null 컬럼
    private String title;

    @Column(nullable = false) // not null 컬럼
    private String content;

    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
