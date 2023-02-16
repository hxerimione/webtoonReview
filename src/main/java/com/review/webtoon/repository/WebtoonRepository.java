package com.review.webtoon.repository;

import com.review.webtoon.entity.Webtoon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebtoonRepository extends MongoRepository<Webtoon,Long> {
    Page<Webtoon> findByPlatformAndDayLike(Pageable pageable, String platform, String day);
    Page<Webtoon> findByPlatform(Pageable pageable,String platform);
    Page<Webtoon> findByDayLike(Pageable pageable, String day);
    Page<Webtoon> findAll(Pageable pageable);
    List<Webtoon> findBySearchKeywordLike(String keyword);
    Optional<Webtoon> findById(Long id);

}