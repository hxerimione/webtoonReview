package com.review.webtoon.repository;

import com.review.webtoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Optional<User> findByUsername(String username);
    User findByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    @Query("select u from User u join fetch u.reviews where u.username = :username")
    User findByUsernameWithReviews(@Param("username") String username);
}
