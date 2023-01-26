package com.review.webtoon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserLogin {
    private String username;
    private String password;
    @Builder
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
