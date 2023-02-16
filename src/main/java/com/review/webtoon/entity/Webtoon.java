package com.review.webtoon.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "myCol")
public class Webtoon {
    @Id
    private Long _id;
    private String author;
    private String[] day;
    private String img;
    private String platform;
    private String searchKeyword;
    private String title;
    private String url;

    //@OneToMany(mappedBy = "webtoon",fetch = FetchType.LAZY)
    //private List<Review> reviews= new ArrayList<>();

    @Builder
    public Webtoon(Long _id, String author, String[] day, String img, String platform, String searchKeyword, String title, String url) {
        this._id = _id;
        this.author = author;
        this.day = day;
        this.img = img;
        this.platform = platform;
        this.searchKeyword = searchKeyword;
        this.title = title;
        this.url = url;
    }
}
