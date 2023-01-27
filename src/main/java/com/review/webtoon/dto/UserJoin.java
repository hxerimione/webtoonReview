package com.review.webtoon.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserJoin extends BaseTimeEntity{
    private String username;
    private String password;
    private String email;
    private Role role;

    @Builder
    public UserJoin(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
}
