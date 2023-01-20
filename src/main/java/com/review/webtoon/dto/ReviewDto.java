package com.review.webtoon.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class ReviewDto extends BaseTimeEntity{
    private String title;
    private String content;
    private Long webtoonId;

    @Builder
    public ReviewDto(){
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
