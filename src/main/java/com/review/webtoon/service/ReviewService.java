package com.review.webtoon.service;

import com.review.webtoon.dto.Review;
import com.review.webtoon.dto.ReviewDto;
import com.review.webtoon.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }
    public Page<Review> findWebtoons(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAll(pageRequest);
    }
    public Optional<Review> findById(Long id){
        return reviewRepository.findById(id);
    }
    public void deleteById(Long id){ reviewRepository.deleteById(id);}
}
