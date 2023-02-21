package com.review.webtoon.entity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse extends BaseTimeEntity{
    private String username;
    private String password;
    @Builder
    public MemberResponse(Member member) {
        this.username = member.getUsername();
        this.password = member.getPassword();
    }
}
