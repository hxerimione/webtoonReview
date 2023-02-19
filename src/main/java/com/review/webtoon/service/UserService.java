package com.review.webtoon.service;

import com.review.webtoon.entity.User;
import com.review.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Long saveUser(User user){
        return userRepository.save(user).getId();
    }
}
