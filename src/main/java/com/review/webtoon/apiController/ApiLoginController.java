package com.review.webtoon.apiController;

import com.review.webtoon.entity.Role;
import com.review.webtoon.entity.UserJoin;
import com.review.webtoon.repository.UserRepository;
import com.review.webtoon.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiLoginController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Data
    static class JoinResponse{
        private Long id;
        public JoinResponse(Long id) {
            this.id = id;
        }
    }
    @PostMapping("/join")
    public JoinResponse join(@RequestBody @Valid UserJoin userJoin){
        userJoin.setRole(Role.ROLE_USER);
        String encodePwd = bCryptPasswordEncoder.encode(userJoin.getPassword());
        userJoin.setPassword(encodePwd);
        Long id = userService.saveUser(userJoin.toEntity());
        return new JoinResponse(id);
    }


}
