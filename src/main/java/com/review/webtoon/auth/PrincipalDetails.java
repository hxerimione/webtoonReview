package com.review.webtoon.auth;

import com.review.webtoon.dto.User;
import com.review.webtoon.userinfo.OAuth2UserInfo;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;
    //private Map<String,Object> attributes;
    private OAuth2UserInfo oAuth2UserInfo;
    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }




    //계정의 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }

    @Override //UserDetails, 계정의 비밀번호 리턴
    public String getPassword() {
        return user.getPassword();
    }

    @Override //UserDetails, 계정의 고유한 값 리턴
    public String getUsername() {
        return user.getUsername();
    }

    @Override //UserDetails, 계정의 만료 여부 리턴
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //UserDetails, 계정의 잠금 여부 리턴
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //UserDetails, 비밀번호 만료 여부 리턴
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //UserDetails, 계정의 활성화 여부 리턴
    public boolean isEnabled() {
        return true;
    }

    @Override //OAuth2User
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }

    @Override //OAuth2User
    public String getName() {
        return oAuth2UserInfo.getProviderId();
    }
}
