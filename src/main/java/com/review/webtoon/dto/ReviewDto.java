package com.review.webtoon.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ReviewDto extends BaseTimeEntity{
    private String title;
    private String content;
    private Long webtoonId;


    public ReviewDto(){

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
