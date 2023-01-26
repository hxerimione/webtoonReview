package com.review.webtoon.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse extends BaseTimeEntity{
    private String username;
    private String password;
    @Builder
    public UserResponse(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
