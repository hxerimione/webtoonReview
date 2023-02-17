package com.review.webtoon.repository;

import com.review.webtoon.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<Review> findById(Long id);
    Review save(Review review);
    Page<Review> findAll(Pageable pageable);
    void deleteById(Long id);
    @Query(value = "select distinct r from Review r " +
            "join fetch r.user " +
            "left join fetch r.hearts " +
            "where r.id = :id")
    Optional<Review> findByIdUsingFetchJoin(@Param("id") Long id);

}
