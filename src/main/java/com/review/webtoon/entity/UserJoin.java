package com.review.webtoon.entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class UserJoin extends BaseTimeEntity{
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
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
