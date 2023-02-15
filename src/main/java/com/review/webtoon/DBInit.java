package com.review.webtoon;

import com.review.webtoon.dto.Heart;
import com.review.webtoon.dto.Review;
import com.review.webtoon.dto.User;
import com.review.webtoon.repository.HeartRepository;
import com.review.webtoon.repository.ReviewRepository;
import com.review.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.review.webtoon.dto.Role.ROLE_USER;

@Component
@RequiredArgsConstructor
public class DBInit {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HeartRepository heartRepository;
    @PostConstruct
    public void init(){

        String encodePwd1 = bCryptPasswordEncoder.encode("pw1234");
        User user1 = User.builder()
                .username("user1")
                .password(encodePwd1)
                .role(ROLE_USER)
                .email("email1@naver.com")
                .build();
        userRepository.save(user1);
        String encodePwd2 = bCryptPasswordEncoder.encode("pw4321");
        User user2 = User.builder()
                .username("user2")
                .password(encodePwd2)
                .role(ROLE_USER)
                .email("email2@naver.com")
                .build();
        userRepository.save(user2);
        Review review1 = Review.builder()
                .title("제목1")
                .content("content1")
                .webtoonId(20619L)
                .img("https://shared-comic.pstatic.net/thumb/webtoon/651673/thumbnail/thumbnail_IMAG10_659b9446-0940-494a-bb5f-5893290a84d0.jpg")
                .user(user1)
                .build();
        reviewRepository.save(review1);
        Review review2 = Review.builder()
                .title("제목2")
                .content("content2")
                .webtoonId(20089L)
                .img("https://shared-comic.pstatic.net/thumb/webtoon/774862/thumbnail/thumbnail_IMAG10_de9a71cc-8f60-4934-af66-69219950185d.jpg")
                .user(user1)
                .build();
        reviewRepository.save(review2);
        Heart like = Heart.builder()
                .review(review1)
                .user(user2)
                .build();
        heartRepository.save(like);
    }

}
