package com.review.webtoon.repository;

import com.review.webtoon.entity.Heart;
import com.review.webtoon.entity.Member;
import com.review.webtoon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart,Long> {
    Optional<Heart> existsByMemberId(Long userId);
    Heart save(Heart heart);
    Optional<Heart> findByMemberAndReview(Member member, Review review);
    @Query("select m from Member m " +
            "join fetch m.hearts as h " +
            "join fetch h.review " +
            "where m.username = :username")
    Member findByUsernameWithReviews(@Param("username") String username);

    void deleteById(Long id);
}
