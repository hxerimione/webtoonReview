package com.review.webtoon.entity;

import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length=50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private Long webtoonId;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Nullable
    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<>();


    public Long getUserId(){
        return member.getId();
    }

    public void setMember() {
        this.member = member;
        member.getReviews().add(this);
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
    public Review(String title, String content, Long webtoonId, String img, Member member){
        this.title = title;
        this.content = content;
        this.webtoonId = webtoonId;
        this.img = img;
        this.member = member;
    }
}
