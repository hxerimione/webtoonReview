package com.review.webtoon.service;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.dto.User;
import com.review.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username).get();
        if(byUsername!=null){
            return new PrincipalDetails(byUsername);
        }
        return null;
    }
}
