package com.review.webtoon.service;

import com.review.webtoon.entity.Review;
import com.review.webtoon.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Long saveReview(Review review){
        return reviewRepository.save(review).getId();
    }
    public Page<Review> findReviews(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAll(pageRequest);
    }
    public Optional<Review> findById(Long id){
        return reviewRepository.findById(id);
    }
    public Long deleteById(Long id){
        reviewRepository.deleteById(id);

        return id;
    }
    public Optional<Review> findByIdUsingFetchJoin(Long id){
        System.out.println("findbyIdUsingFetchJoin");
        return reviewRepository.findByIdUsingFetchJoin(id);
    }
    public Page<Review> findReviewsOrderByHeartsLength(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        return reviewRepository.findAllOrderByHeartsLength(pageRequest);

    }


}
