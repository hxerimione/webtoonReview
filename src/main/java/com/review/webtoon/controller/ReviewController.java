package com.review.webtoon.controller;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.entity.*;
import com.review.webtoon.service.HeartService;
import com.review.webtoon.service.ReviewService;
import com.review.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final WebtoonService webtoonService;
    private final HeartService heartService;
    @GetMapping("/review")
    public String list(@PageableDefault(sort = {"title"},direction = Sort.Direction.ASC,size =12) Pageable pageable
                       ,@RequestParam(defaultValue = "1") int page,Model model,
                       @RequestParam(value = "order",required = false)String order){
        Page<Review> reviews;
        if (order== null){
            reviews = reviewService.findReviews(pageable.getPageNumber(), pageable.getPageSize());

        }
        else if (order.equals("heart")){
            reviews= reviewService.findReviewsOrderByHeartsLength(pageable.getPageNumber(), pageable.getPageSize());

        }
        else{
            reviews = reviewService.findReviews(pageable.getPageNumber(), pageable.getPageSize());

        }
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
    public String read(@PathVariable Long id, Model model,Authentication authentication){
        boolean heart = false;
        if (authentication != null){
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            Member member = principal.getMember();
            if (!(heartService.isNotAlreadyLike(member,reviewService.findById(id).get()))){
                heart = true;
            }
        }
        Review review = reviewService.findById(id).get();
        model.addAttribute("review",review);
        model.addAttribute("heart",heart);

        return "review/view";
    }
    @GetMapping("/review/new")
    public String createReview(Model model,Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if (authentication == null){
            MessageDto message = new MessageDto("로그인을 한 사용자만 이용할 수 있습니다.", "/loginForm", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }
        model.addAttribute("form",new ReviewDto());
        return "review/createReview";
    }
    //
    @PostMapping("/review/new")
    public String createReview(@Valid ReviewDto dto, Authentication authentication){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member member = principal.getMember();
        Webtoon webtoon=webtoonService.findWebtoonById(dto.getWebtoonId()).get();
        String img = webtoon.getImg();
        Review review = Review
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .webtoonId(dto.getWebtoonId())
                .img(img)
                .member(member)
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
    //수정
    @GetMapping("/update/{id}")
    public String update(Authentication authentication,@PathVariable Long id, Model model){

        if (authentication == null){
            MessageDto message = new MessageDto("로그인을 한 사용자만 이용할 수 있습니다.", "/loginForm", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Member member = principal.getMember();

        Review review = reviewService.findById(id).get();
        if (member.getId() != review.getMember().getId()){
            MessageDto message = new MessageDto("본인이 작성한 글만 수정할 수 있습니다.", "/review", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }

        model.addAttribute(review);
        return "review/updateReview";

    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,@Valid ReviewDto form){
        System.out.println("happy");
        Review review = reviewService.findById(id).get();
        review.update(form);
        reviewService.saveReview(review);
        return "redirect:/review";
    }
    @DeleteMapping("/review/{id}")
    public String delete(Authentication authentication,@PathVariable Long id,Model model){
        if (authentication == null){
            MessageDto message = new MessageDto("로그인을 한 사용자만 이용할 수 있습니다.", "/loginForm", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Member member = principal.getMember();
        Review review = reviewService.findById(id).get();
        if (member.getId() != review.getMember().getId()){
            MessageDto message = new MessageDto("본인이 작성한 글만 삭제할 수 있습니다.", "/review", RequestMethod.GET, null);
            model.addAttribute("params",message);
            return "messageRedirect";
        }

        reviewService.deleteById(id);
        return "redirect:/review";
    }
}
