package com.review.webtoon.repository;

import com.review.webtoon.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //Optional<Member> findByUsername(String username);
    Optional<Member> findByUsername(String username);

    @Override
    <S extends Member> S save(S entity);

    @Query("select u from Member u join fetch u.reviews where u.username = :username")
    Member findByUsernameWithReviews(@Param("username") String username);

    @EntityGraph(attributePaths = "reviews")
    Optional<Member> findMemberEntityGraphByUsername(@Param("username") String username);

}
