package com.review.webtoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {
    @GetMapping("hello")
    public String home(){
        return "hello";
    }
}
