package com.review.webtoon.repository;

import com.review.webtoon.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //Optional<Member> findByUsername(String username);
    Member findByUsername(String username);

    @Override
    <S extends Member> S save(S entity);

    @Query("select u from Member u join fetch u.reviews where u.username = :username")
    Member findByUsernameWithReviews(@Param("username") String username);
}
