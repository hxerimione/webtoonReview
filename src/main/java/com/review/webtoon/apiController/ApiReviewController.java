package com.review.webtoon.apiController;

import com.review.webtoon.auth.PrincipalDetails;
import com.review.webtoon.entity.*;
import com.review.webtoon.service.HeartService;
import com.review.webtoon.service.ReviewService;
import com.review.webtoon.service.WebtoonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiReviewController {
    private final ReviewService reviewService;
    private final WebtoonService webtoonService;
    private final HeartService heartService;
    @GetMapping("/review")
    public List<ReviewResponse> list(@PageableDefault(sort = {"title"},direction = Sort.Direction.ASC,size =12) Pageable pageable){
        Page<Review> reviews = reviewService.findReviews(pageable.getPageNumber(), pageable.getPageSize());
        List<ReviewResponse> result = reviews.stream()
                .map(o->new ReviewResponse(o))
                .collect(Collectors.toList());
        return result;
    }

    @Data
    static class ReviewResponse{
        private String title;
        private String content;
        private String img;
        private String username;
        public ReviewResponse(Review review){
            this.content = review.getContent();
            this.title = review.getTitle();
            this.img = review.getImg();
            this.username = review.getUser().getUsername();
        }
    }
    @GetMapping("/review/{id}")
    public ReviewDto read(@PathVariable Long id){
        Review review = reviewService.findByIdUsingFetchJoin(id).get();
        ReviewDto result = new ReviewDto(review);
        return result;
    }

    @PostMapping("/review/new")
    public CreateReviewResponse createReview(@RequestBody @Valid CreateReviewRequest request){
        Webtoon webtoon=webtoonService.findWebtoonById(request.webtoonId).get();
        String img = webtoon.getImg();
        Review review = Review.builder()
                .title(request.title)
                .content(request.content)
                .webtoonId(request.webtoonId)
                .img(img)
                .build();

        Long id = reviewService.saveReview(review);
        return new CreateReviewResponse(id);
    }
    @Data
    static class CreateReviewRequest{
        private String title;
        private String content;
        private Long webtoonId;

    }
    @Data
    static class CreateReviewResponse{
        private Long id;
        public CreateReviewResponse(Long id){
            this.id = id;
        }
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

    @PutMapping("/review/{id}")
    public UpdateReviewResponse update(@PathVariable Long id,@RequestBody @Valid UpdateReviewRequest request){

        Review review = reviewService.findById(id).get();
        ReviewDto reviewDto = ReviewDto.builder().title(request.title).content(request.content).build();
        review.update(reviewDto);
        Long updateId = reviewService.saveReview(review);
        return new UpdateReviewResponse(updateId);
    }
    @Data
    static class UpdateReviewRequest{
        private String title;
        private String content;
    }

    @Data
    static class UpdateReviewResponse{
        private Long id;
        public UpdateReviewResponse(Long id){
            this.id = id;
        }
    }
    @DeleteMapping("/review/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        Review review = reviewService.findById(id).get();

        reviewService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

