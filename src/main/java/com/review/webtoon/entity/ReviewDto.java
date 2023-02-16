package com.review.webtoon.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
public class ReviewDto extends BaseTimeEntity{
    private String title;
    private String content;
    private Long webtoonId;

    private String author;
    private String img;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<Heart> hearts;
    public ReviewDto(){

    }
    public ReviewDto(Review review){
        this.title = review.getTitle();
        this.content = review.getContent();
        this.webtoonId = review.getWebtoonId();
        this.author = review.getUser().getUsername();
        this.img = review.getImg();
        this.createdDate = review.getCreatedDate();
        this.modifiedDate = review.getModifiedDate();
        this.hearts = review.getHearts();

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
