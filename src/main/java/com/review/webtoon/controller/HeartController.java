package com.review.webtoon.controller;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.dto.MessageDto;
import com.review.webtoon.dto.User;
import com.review.webtoon.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;
    @PostMapping("review/heart/{reviewId}")
    @ResponseBody
    public boolean like(@PathVariable Long reviewId, Authentication authentication){
        System.out.println("hi");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        // 저장 true, 삭제 false
        boolean result = heartService.addHeart(user.getId(),reviewId);
        return result;
    }
    @GetMapping("/hearts")
    public String hearts(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        if (principalDetails == null){
            MessageDto message = new MessageDto("로그인을 한 사용자만 이용할 수 있습니다.", "/loginForm", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }
        //PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        //User user = principal.getUser();
        String username = principalDetails.getUsername();
        User byUsernameWithHearts = heartService.findByUserWithReview(username);
        model.addAttribute("hearts",byUsernameWithHearts.getHearts());
        return "hearts";
    }
}
