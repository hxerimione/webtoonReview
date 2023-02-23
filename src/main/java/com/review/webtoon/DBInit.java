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
/*
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
                .webtoonId(30000L)
                .img("https://dn-img-page.kakao.com/download/resource?kid=ctsChS/hzmU2G9wvy/kACXAoO74nAoyrjhyrQvL1&filename=th3")
                .member(member2)
                .build();
        reviewRepository.save(review1);

            Review review = Review.builder()
                    .title("title0")
                    .content("content0")
                    .webtoonId(20000L)
                    .img("https://dn-img-page.kakao.com/download/resource?kid=MyO5T/hzmU1nFTG6/nnlH21LT4mDo1QTcVIhea1&filename=th3")
                    .member(member2)
                    .build();
            reviewRepository.save(review);

        Review review3 = Review.builder()
                .title("title3")
                .content("content3")
                .webtoonId(30003L)
                .img("https://dn-img-page.kakao.com/download/resource?kid=ddxk7R/hyxJJRQ2xs/lSqyzvbSBYQKTydrwpdj5k&filename=th3")
                .member(member1)
                .build();
        reviewRepository.save(review3);

        Review review2 = Review.builder()
                .title("title1")
                .content("content2")
                .webtoonId(20089L)
                .img("https://shared-comic.pstatic.net/thumb/webtoon/774862/thumbnail/thumbnail_IMAG10_de9a71cc-8f60-4934-af66-69219950185d.jpg")
                .member(member1)
                .build();
        reviewRepository.save(review2);
        Review review4 = Review.builder()
                .title("title4")
                .content("content4")
                .webtoonId(20089L)
                .img("https://dn-img-page.kakao.com/download/resource?kid=xWPEa/hzR1U52IFk/pXvV5VT3uek6YvkSGUVJI1&filename=th3")
                .member(member1)
                .build();
        reviewRepository.save(review4);
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

 */
    }//special 2 special first 1


}
