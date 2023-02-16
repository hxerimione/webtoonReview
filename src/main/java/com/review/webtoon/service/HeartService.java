package com.review.webtoon.service;

import com.review.webtoon.entity.Heart;
import com.review.webtoon.entity.Review;
import com.review.webtoon.entity.User;
import com.review.webtoon.repository.HeartRepository;
import com.review.webtoon.repository.ReviewRepository;
import com.review.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    public boolean addHeart(Long userId, Long reviewId){
        User user = userRepository.findById(userId).get();
        Review review = reviewRepository.findById(reviewId).get();
        if (isNotAlreadyLike(user,review)){
            heartRepository.save(new Heart(user,review));
            return true;
        }else{
            Long id = heartRepository.findByUserAndReview(user, review).get().getId();
            heartRepository.deleteById(id);
            return false;
        }
    }
    public boolean isNotAlreadyLike(User user, Review review){
        //좋아요 안눌려있으면 true
        return heartRepository.findByUserAndReview(user,review).isEmpty();
    }
    public User findByUserWithReview(String username){
        return heartRepository.findByUsernameWithReviews(username);
    }
}
