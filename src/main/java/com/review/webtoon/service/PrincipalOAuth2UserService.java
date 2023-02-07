package com.review.webtoon.service;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.dto.Role;
import com.review.webtoon.dto.User;
import com.review.webtoon.repository.UserRepository;
import com.review.webtoon.userinfo.KakaoUserInfo;
import com.review.webtoon.userinfo.NaverUserInfo;
import com.review.webtoon.userinfo.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();
        if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }else if (provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_"+ providerId;

        String uuid = UUID.randomUUID().toString().substring(0,6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        //String email = oAuth2User.getAttribute("email");
        String email = oAuth2UserInfo.getEmail();
        Role role = Role.ROLE_USER;
        System.out.println(email);
        User byUsername = userRepository.findByUsername(username);

        if(userRepository.findByUsername(username) == null){
            byUsername = User.oauth2Register()
                    .username(username).password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            userRepository.save(byUsername);
        }
        return new PrincipalDetails(byUsername, oAuth2UserInfo);
    }
}
