package com.review.webtoon.apiController;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.entity.Heart;
import com.review.webtoon.entity.Member;
import com.review.webtoon.service.HeartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiHeartController {
    private final HeartService heartService;

    @Data
    static class HeartResponse{
        private Long reviewId;
        private String reviewTitle;

        public HeartResponse(Heart heart) {
            this.reviewId = heart.getReview().getId();
            this.reviewTitle = heart.getReview().getTitle();

        }
    }

    @PostMapping("/heart/{reviewId}")
    public boolean like(@PathVariable Long reviewId, Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member member = principal.getMember();
        // 저장 true, 삭제 false
        boolean result = heartService.addHeart(member.getId(),reviewId);
        return result;
    }
    @GetMapping("/hearts")
    public List<HeartResponse> hearts(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String username = principalDetails.getUsername();
        Member byUsernameWithHearts = heartService.findByUserWithReview(username);
        List<Heart> heartsList = byUsernameWithHearts.getHearts();
        List<HeartResponse> result = heartsList.stream()
                .map(o->new HeartResponse(o))
                .collect(Collectors.toList());
        return result;
    }
}
