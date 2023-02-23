package com.review.webtoon.repository;

import com.review.webtoon.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Query(value = "select distinct r from Review r " +
            "join fetch r.member " +
            "group by r.id " +
            "order by r.id desc",countQuery = "select count(r) from Review r")
    Page<Review> findAll(Pageable pageable);
    void deleteById(Long id);
    @Query(value = "select distinct r from Review r " +
            "join fetch r.member " +
            "left join fetch r.hearts " +
            "where r.id = :id")
    Optional<Review> findByIdUsingFetchJoin(@Param("id") Long id);

    @Query(value = "select distinct r from Review r " +
            "join fetch r.member m " +
            "left join fetch r.hearts h " +
            "group by h.id,r.id " +
            "order by h.size desc",
            countQuery = "select count(r) from Review r")
    Page<Review> findAllOrderByHeartsLength(Pageable pageable);
}
