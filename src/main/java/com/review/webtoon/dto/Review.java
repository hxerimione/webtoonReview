package com.review.webtoon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(length=50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private Long webtoonId;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser() {
        this.user = user;
        user.getReviews().add(this);
    }
    public Review(ReviewDto reviewDto){
        this.title = reviewDto.getTitle();
        this.content = reviewDto.getContent();
    }
    public void update(ReviewDto reviewDto){
        this.title = reviewDto.getTitle();
        this.content = reviewDto.getContent();
    }
    @Builder
    public Review(String title, String content, Long webtoonId,String img,User user){
        this.title = title;
        this.content = content;
        this.webtoonId = webtoonId;
        this.img = img;
        this.user = user;
        //this.webtoon = webtoon;
    }
}
