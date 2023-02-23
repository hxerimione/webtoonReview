package com.review.webtoon.controller;

import com.review.webtoon.entity.Pagination;
import com.review.webtoon.entity.Webtoon;
import com.review.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HubController {
    private final WebtoonService webtoonService;

    @GetMapping("/hub")
    public String findAllWebtoons(
            @RequestParam(required = false) String platform,
            @PageableDefault(sort = {"title"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String keyword,
            Model model) {
        if (keyword == null) {
            if (platform == null) {
                Page<Webtoon> webtoons = webtoonService.findWebtoons(pageable.getPageNumber(), pageable.getPageSize());
                List<Webtoon> webtoonList = new ArrayList<Webtoon>();
                Pagination pagination = new Pagination(webtoons.getTotalPages(), page);
                if (webtoons.hasContent()) {
                    webtoonList = webtoons.getContent();
                }
                model.addAttribute("webtoons", webtoonList);
                model.addAttribute("pagination", pagination);
                return "hub";
            } else if (platform.equals("naver")) {
                Page<Webtoon> webtoons = webtoonService.findWebtoonByPlatform(pageable.getPageNumber(), pageable.getPageSize(), "네이버 웹툰");
                List<Webtoon> webtoonList = new ArrayList<Webtoon>();
                Pagination pagination = new Pagination(webtoons.getTotalPages(), page);
                System.out.println(webtoons);
                if (webtoons.hasContent()) {
                    webtoonList = webtoons.getContent();
                }
                model.addAttribute("webtoons", webtoonList);
                model.addAttribute("pagination", pagination);
                return "hub";
            } else if (platform.equals("kakao")) {
                Page<Webtoon> webtoons = webtoonService.findWebtoonByPlatform(pageable.getPageNumber(), pageable.getPageSize(), "카카오 페이지");
                List<Webtoon> webtoonList = new ArrayList<Webtoon>();
                Pagination pagination = new Pagination(webtoons.getTotalPages(), page);
                System.out.println(webtoons);
                if (webtoons.hasContent()) {
                    webtoonList = webtoons.getContent();
                }
                model.addAttribute("webtoons", webtoonList);
                model.addAttribute("pagination", pagination);
                return "kakaohub";
            }
        } else {
            List<Webtoon> webtoonList = webtoonService.findWebtoonBySearchKeyword(keyword);
            Pagination pagination = new Pagination(pageable.getPageSize(), 1);
            model.addAttribute("webtoons", webtoonList);
            model.addAttribute("pagination", pagination);
            return "keyword";
        }
        return null;
    }




}
