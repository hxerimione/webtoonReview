package com.review.webtoon.repository;

import com.review.webtoon.dto.Heart;
import com.review.webtoon.dto.Review;
import com.review.webtoon.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart,Long> {
    Optional<Heart> existsByUserId(Long userId);
    Heart save(Heart heart);
    Optional<Heart> findByUserAndReview(User user, Review review);
    @Query("select u from User u join fetch u.hearts where u.username = :username")
    User findByUsernameWithReviews(@Param("username") String username);

    void deleteById(Long id);
}
