package com.review.webtoon.apiController;

import com.review.webtoon.entity.Webtoon;
import com.review.webtoon.service.WebtoonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WebtoonService service;


    @GetMapping("/api")
    public Page<Webtoon> findWebtoon(@PageableDefault(sort = {"_id"},direction = Sort.Direction.ASC,size =9) Pageable pageable,
                                     @RequestParam(required = false) String platform,
                                     @RequestParam(required = false) String day){
        //page 기본값 0, size 기본값 10
        if (platform==null && day==null){
            return service.findWebtoons(pageable.getPageNumber(), pageable.getPageSize());
        }else if (platform == null){
            return service.findWebtoonByDayLike(pageable.getPageNumber(), pageable.getPageSize(), day);
        }else if (day == null){
            return service.findWebtoonByPlatform(pageable.getPageNumber(), pageable.getPageSize(), platform);
        }else {
            return service.findWebtoonByPlatformAndDayLike(pageable.getPageNumber(), pageable.getPageSize(), platform, day);
        }
    }
    @GetMapping("/api/search")
    public List<Webtoon> searchWebtoon(@RequestParam String keyword) {
        return service.findWebtoonBySearchKeyword(keyword);
    }
    }