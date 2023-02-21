package com.review.webtoon;

import com.review.webtoon.entity.Heart;
import com.review.webtoon.entity.Member;
import com.review.webtoon.entity.Review;
import com.review.webtoon.repository.HeartRepository;
import com.review.webtoon.repository.ReviewRepository;
import com.review.webtoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.review.webtoon.entity.Role.ROLE_USER;

@Component
@RequiredArgsConstructor
public class DBInit {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HeartRepository heartRepository;
    @PostConstruct
    public void init(){

        String encodePwd1 = bCryptPasswordEncoder.encode("pw1234");
        Member member1 = Member.builder()
                .username("member1")
                .password(encodePwd1)
                .role(ROLE_USER)
                .email("email1@naver.com")
                .build();
        memberRepository.save(member1);
        String encodePwd2 = bCryptPasswordEncoder.encode("pw4321");
        Member member2 = Member.builder()
                .username("member2")
                .password(encodePwd2)
                .role(ROLE_USER)
                .email("email2@naver.com")
                .build();
        memberRepository.save(member2);
        Review review1 = Review.builder()
                .title("special first")
                .content("special first")
                .webtoonId(20000L)
                .img("https://shared-comic.pstatic.net/thumb/webtoon/651673/thumbnail/thumbnail_IMAG10_659b9446-0940-494a-bb5f-5893290a84d0.jpg")
                .member(member2)
                .build();
        reviewRepository.save(review1);
        for (int i=0;i<30;i++){
            Review review = Review.builder()
                    .title("제목"+i)
                    .content("content"+i)
                    .webtoonId(20000L+(long) i)
                    .img("https://shared-comic.pstatic.net/thumb/webtoon/651673/thumbnail/thumbnail_IMAG10_659b9446-0940-494a-bb5f-5893290a84d0.jpg")
                    .member(member2)
                    .build();
            reviewRepository.save(review);
        }

        Review review2 = Review.builder()
                .title("special")
                .content("special")
                .webtoonId(20089L)
                .img("https://shared-comic.pstatic.net/thumb/webtoon/774862/thumbnail/thumbnail_IMAG10_de9a71cc-8f60-4934-af66-69219950185d.jpg")
                .member(member1)
                .build();
        reviewRepository.save(review2);
        Heart like = Heart.builder()
                .review(review2)
                .member(member2)
                .build();
        heartRepository.save(like);
        Heart like2 = Heart.builder()
                .review(review2)
                .member(member1)
                .build();
        heartRepository.save(like2);
        Heart like3 = Heart.builder()
                .review(review1)
                .member(member1)
                .build();
        heartRepository.save(like3);
    }//special 2 special first 1
}
