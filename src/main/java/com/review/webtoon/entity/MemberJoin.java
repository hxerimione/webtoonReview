package com.review.webtoon.entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class MemberJoin extends BaseTimeEntity{
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
    private Role role;

    @Builder
    public MemberJoin(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
}
