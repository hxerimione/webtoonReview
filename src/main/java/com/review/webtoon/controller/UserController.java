package com.review.webtoon.controller;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.dto.*;
import com.review.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    @GetMapping("/userReview")
    public String userReview(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        User user = principal.getUser();
        System.out.println(user.getReviews());
        model.addAttribute("userReview",user.getReviews());
        return "userReview";
    }
    @PostMapping("/join")
    public String join(@ModelAttribute UserJoin userRequest){
        userRequest.setRole(Role.ROLE_USER);
        //비밀번호 암호화
        String encodePwd = bCryptPasswordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodePwd);
        //DTO to Entity
        userRepository.save(userRequest.toEntity());
        return "redirect:/loginForm";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }
    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }

    @GetMapping("/form/loginInfo")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        User user = principal.getUser();
        System.out.println(user);
        //user, user1은 같은 유저
        User user1 = principalDetails.getUser();
        System.out.println(user1);
        return UserResponse.builder().user(user).build().toString();
    }

    @GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        //attributes, attributes1은 같은 attributes
        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();

        return attributes.toString();
    }

    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication,@AuthenticationPrincipal PrincipalDetails principalDetails){
        String result = "";

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        //provider==null이면 플랫폼이 없다는 뜻 == formlogin 이다.
        if(principal.getUser().getProvider() == null){
            result += "Form 로그인 : " + principal;
        }else{
            result += "OAuth2 로그인 : " + principal;
        }
        return result;
    }


}