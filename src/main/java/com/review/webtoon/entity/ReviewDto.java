package com.review.webtoon.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class ReviewDto extends BaseTimeEntity{
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String title;
    private String content;
    private Long webtoonId;
    private String username;
    private int hearts;
    private String img;
    public ReviewDto(){

    }
    public ReviewDto(Review review){
        this.createdDate = review.getCreatedDate();
        this.modifiedDate = review.getModifiedDate();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.webtoonId = review.getWebtoonId();
        this.img = review.getImg();
        this.hearts = review.getHearts().size();
        this.username = review.getMember().getUsername();
    }
    @Builder
    public ReviewDto(String title, String content, Long webtoonId){
        this.title = title;
        this.content =content;
        this.webtoonId = webtoonId;
    }
    public Review toEntity(){
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }
}
