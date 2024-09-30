package com.itschool.springbootdeveloper.dto;

import com.itschool.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO : Data Transfer Object
// 계층끼리 데이터를 교환하기 위해 사용하는 객체임
// 단순하게 데이터를 전달하기 위해 사용하는 전달자 역할 (비즈니스 로직 작성 x)
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    
    private String title;
    private String content;
    
    public Article toEntity() {
        // return new Article(this.title, this.content);
        return Article.builder() // 빌더 패턴을 사용하면 직관적으로 생성 가능
                .title(title)
                .content(content)
                .build();
    }

    @Override
    public String toString() {
        return "AddArticleRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
