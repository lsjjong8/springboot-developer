package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.ArticleResponse;
import com.itschool.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequestMapping("/api/articles")
public class BlogApiController {

    private final BlogService blogService;

    @GetMapping("{id}")
    public String method1(@PathVariable Long id) {
        return "글 조회 테스트";
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        // 방식 1
        /*List<Article> articleList = blogService.findAll();
        List<ArticleResponse> articles = new ArrayList<>();

        for(Article article : articleList) {
            articles.add(new ArticleResponse(article));
        }*/

        // 방식 2
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(article -> new ArticleResponse(article)) // ClassName::instanceMethod 인스턴스 메서드 참조(클래스 이름), ArticleResponse::new
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @PutMapping("{id}")
    public String method2(@PathVariable Long id) {
        return "글 수정 테스트";
    }

    @DeleteMapping("{id}")
    public String method3(@PathVariable Long id) {
        return "글 삭제 테스트";
    }
}
