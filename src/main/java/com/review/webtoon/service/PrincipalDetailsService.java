package com.review.webtoon.service;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.entity.Member;
import com.review.webtoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    //login시 호출
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member byUsername = memberRepository.findByUsername(username).get();
        if(byUsername!=null){
            return new PrincipalDetails(byUsername);
        }
        System.out.println("hello");
        return null;
    }
}
