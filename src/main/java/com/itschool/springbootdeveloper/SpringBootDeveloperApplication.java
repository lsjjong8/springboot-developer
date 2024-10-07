package com.itschool.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // created_at, updated_at 자동 업데이트
@SpringBootApplication // 스프링 부트 사용에 필요한 기본 설정을 해줌
public class SpringBootDeveloperApplication {
    public static void main(String[] args) { // 기존의 main 메서드(실행부), 여기에서 스프링 부트가 시작됨
        // SpringApplication.run() 메서드는 애플리케이션을 실행
        // 첫 번째 인수는 스프링 부트 애플리케이션의 메인 클래스로 사용할 클래스
        // 두 번째 인수는 커맨드 라인의 인수들을 전달
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
