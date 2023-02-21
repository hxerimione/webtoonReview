package com.review.webtoon.service;

import com.review.webtoon.entity.Member;
import com.review.webtoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long saveUser(Member member){
        return memberRepository.save(member).getId();
    }
}
