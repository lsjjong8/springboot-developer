package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.UpdateArticleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest extends MockMvcTest<Article, Long> {

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // POST Body,객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = baseRepository.findAll();

        // import static org.assertj.core.api.Assertions.assertThat;
        assertThat(articles.size()).isEqualTo(1); // 크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception {
        // @BeforeEach에서 baseRepository.deleteAll()을 수행하기 때문에 테이블이 초기화 돼있음
        
        // given : 테스트를 위한 준비가 주어져야 함
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        Long beforeCount = baseRepository.count(); // 0

        baseRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        Long afterCount = baseRepository.count(); // 1

        // when : 테스트를 위한 수행
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON));


        // then : 그러면 결과를 검증해볼까요?
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk()) // 응답코드가 200이 맞는가?
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(title));

        assertThat(beforeCount+1).isEqualTo(afterCount);
    }

    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception {
        // given : 테스트를 위한 준비가 주어져야 함
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Long beforeCount = baseRepository.count(); // 0

        Article article = baseRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        Long afterCount = baseRepository.count(); // 1

        // when : 테스트를 위한 수행
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, article.getId())
                .accept(MediaType.APPLICATION_JSON));

        // then : 그러면 결과를 검증해볼까요?
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk()) // 응답코드가 200이 맞는가?
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title));

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }

    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        // given : 테스트를 위한 준비가 주어져야 함
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article article = baseRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        Long beforeCount = baseRepository.count(); // 1

        // when : 테스트를 위한 수행
        mockMvc.perform(MockMvcRequestBuilders.delete(url, article.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Long afterCount = baseRepository.count(); // 0

        // then : 그러면 결과를 검증해볼까요?
        List<Article> articles = baseRepository.findAll();

        assertThat(articles.isEmpty());
        assertThat(beforeCount - 1).isEqualTo(afterCount);
    }

    @DisplayName("updateArticle: 블로그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = baseRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        Long beforeCount = baseRepository.count(); // 1

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

        // when
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request))); // request를 json형태의 String으로 직렬화

        Long afterCount = baseRepository.count(); // 1

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Article article = baseRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);

        assertThat(beforeCount).isEqualTo(afterCount);
    }
}