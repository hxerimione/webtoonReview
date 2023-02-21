package com.review.webtoon.apiController;

import com.review.webtoon.entity.Role;
import com.review.webtoon.entity.MemberJoin;
import com.review.webtoon.repository.MemberRepository;
import com.review.webtoon.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiLoginController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Data
    static class JoinResponse{
        private Long id;
        public JoinResponse(Long id) {
            this.id = id;
        }
    }
    @PostMapping("/join")
    public JoinResponse join(@RequestBody @Valid MemberJoin memberJoin){
        memberJoin.setRole(Role.ROLE_USER);
        String encodePwd = bCryptPasswordEncoder.encode(memberJoin.getPassword());
        memberJoin.setPassword(encodePwd);
        Long id = memberService.saveUser(memberJoin.toEntity());
        return new JoinResponse(id);
    }


}
