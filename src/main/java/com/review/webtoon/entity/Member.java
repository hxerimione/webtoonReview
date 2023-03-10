package com.review.webtoon.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
    @GeneratedValue
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;

    private String provider; //oauth2를 이용할 경우 어떤 플랫폼을 이용할지 Ex naver,kakao
    private String providerId; //oauth2를 이용할 경우 아이디값

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<>();
    @Builder
    public Member(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String username, String password, String email, Role role, String provider, String providerId){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

}
