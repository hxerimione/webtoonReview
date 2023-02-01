package com.review.webtoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDto {
    //알림메시지
    private String message;
    private String redirectUri;
    private RequestMethod method;
    private Map<String,Object> data;
}
