package com.review.webtoon.controller;

import com.review.webtoon.dto.Pagination;
import com.review.webtoon.dto.Webtoon;
import com.review.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HubController {
    private final WebtoonService webtoonService;
    @GetMapping("/hub")
    public String findAllWebtoons(@PageableDefault(sort = {"title"},direction = Sort.Direction.ASC,size =12) Pageable pageable,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(required = false) String keyword,
                                  Model model){
        if (keyword==null){
            Page<Webtoon> webtoons = webtoonService.findWebtoons(pageable.getPageNumber(), pageable.getPageSize());
            List<Webtoon> webtoonList = new ArrayList<Webtoon>();
            Pagination pagination = new Pagination(webtoons.getTotalPages(),page);
            if(webtoons.hasContent()){
                webtoonList = webtoons.getContent();
            }
            model.addAttribute("webtoons",webtoonList);
            model.addAttribute("pagination",pagination);
            return "hub";
        }else{
            List<Webtoon> webtoonList = webtoonService.findWebtoonBySearchKeyword(keyword);
            Pagination pagination = new Pagination(pageable.getPageSize(),1);
            model.addAttribute("webtoons",webtoonList);
            model.addAttribute("pagination",pagination);
            return "keyword";
        }

    }
    @GetMapping("/hub/naver")
    public String findWebtoonByNaver(@PageableDefault(sort = {"title"},direction = Sort.Direction.ASC,size=12) Pageable pageable,
                                        @RequestParam(defaultValue = "1") int page,
                                        Model model){
        Page<Webtoon> webtoons = webtoonService.findWebtoonByPlatform(pageable.getPageNumber(), pageable.getPageSize(),"네이버 웹툰");
        List<Webtoon> webtoonList = new ArrayList<Webtoon>();
        Pagination pagination = new Pagination(webtoons.getTotalPages(),page);
        System.out.println(webtoons);
        if(webtoons.hasContent()){
            webtoonList = webtoons.getContent();
        }
        model.addAttribute("webtoons",webtoonList);
        model.addAttribute("pagination",pagination);
        return "naverhub";
    }


}
