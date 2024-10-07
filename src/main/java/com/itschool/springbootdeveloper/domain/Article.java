package com.itschool.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // 엔티티로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false) // not null 컬럼
    private String title;

    @Column(nullable = false) // not null 컬럼
    private String content;

    @CreatedDate
    @Column(name="created_at") // 엔티티가 생성될 때 생성 시간 저장
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name="updated_at") // 엔티티가 수정될 때 수정 시간 저장
    private LocalDateTime updatedAt;
    
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
