package com.review.webtoon.service;

import com.review.webtoon.entity.Heart;
import com.review.webtoon.entity.Member;
import com.review.webtoon.entity.Review;
import com.review.webtoon.repository.HeartRepository;
import com.review.webtoon.repository.ReviewRepository;
import com.review.webtoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    public boolean addHeart(Long userId, Long reviewId){
        Member member = memberRepository.findById(userId).get();
        Review review = reviewRepository.findById(reviewId).get();
        if (isNotAlreadyLike(member,review)){
            heartRepository.save(new Heart(member,review));
            return true;
        }else{
            Long id = heartRepository.findByMemberAndReview(member, review).get().getId();
            heartRepository.deleteById(id);
            return false;
        }
    }
    public boolean isNotAlreadyLike(Member member, Review review){
        //좋아요 안눌려있으면 true
        return heartRepository.findByMemberAndReview(member,review).isEmpty();
    }
    public Member findByUserWithReview(String username){
        return heartRepository.findByUsernameWithReviews(username);
    }
}
