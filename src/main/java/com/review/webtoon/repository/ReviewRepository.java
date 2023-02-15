package com.review.webtoon.repository;

import com.review.webtoon.dto.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<Review> findById(Long id);
    Review save(Review review);
    Page<Review> findAll(Pageable pageable);
    void deleteById(Long id);
}
