package com.itschool.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardViewController {
    @GetMapping("/board")
    public String getArticles(Model model) {
        return "board"; // board.html이라는 뷰 조회
    }
}
