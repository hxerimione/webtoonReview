package com.review.webtoon.controller;

import com.review.webtoon.dto.Pagination;
import com.review.webtoon.dto.Review;
import com.review.webtoon.dto.ReviewDto;
import com.review.webtoon.dto.Webtoon;
import com.review.webtoon.service.ReviewService;
import com.review.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final WebtoonService webtoonService;
    @GetMapping("/review")
    public String list(@PageableDefault(sort = {"title"},direction = Sort.Direction.ASC,size =12) Pageable pageable
                       ,@RequestParam(defaultValue = "1") int page,Model model){
        Page<Review> reviews= reviewService.findWebtoons(pageable.getPageNumber(), pageable.getPageSize());
        List<Review> reviewList = new ArrayList<Review>();
        if(reviews.hasContent()){
            reviewList = reviews.getContent();
        }
        Pagination pagination = new Pagination(reviews.getTotalPages(),page);
        model.addAttribute("reviews",reviewList);
        model.addAttribute("pagination",pagination);
        return "review/reviews";
    }
    @GetMapping("/review/{id}")
    public String read(@PathVariable Long id, Model model){
        Review review = reviewService.findById(id).get();
        model.addAttribute("review",review);
        return "review/view";
    }
    @GetMapping("/review/new")
    public String createReview(Model model){
        model.addAttribute("form", new ReviewDto());
        //webtoon list
        return "review/createReview";
    }
    @PostMapping("/review/new")
    public String createReview(@Valid ReviewDto dto){
        System.out.println("hello");
        System.out.println(dto.getTitle());
        System.out.println(dto.getWebtoonId());
        Webtoon webtoon=webtoonService.findWebtoonById(dto.getWebtoonId()).get();
        String img = webtoon.getImg();
        Review review = Review
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .webtoonId(dto.getWebtoonId())
                .img(img)
                .build();
        reviewService.saveReview(review);
        return "redirect:/review";
    }
    @GetMapping("/review/selectWebtoon")
    public String selectWebtoon(@RequestParam(required = false) String keyword,
                                Model model){
        if (keyword==null){
            List<Webtoon> webtoonList= new ArrayList<Webtoon>();
            model.addAttribute("webtoons",webtoonList);
            return "review/selectWebtoon";
        }else {
            List<Webtoon> webtoonList = webtoonService.findWebtoonBySearchKeyword(keyword);
            model.addAttribute("webtoons", webtoonList);
            return "review/selectWebtoon";
        }
    }
}
