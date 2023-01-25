package com.review.webtoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login/oauth2/code")
public class LoginController {
    @GetMapping("/kakao")
    public String kakaoOauthRedirect(@RequestParam String code){
        return "카카오 로그인 인증 완료, code : "+code;
    }

}
