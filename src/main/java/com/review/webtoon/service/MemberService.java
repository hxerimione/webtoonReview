package com.review.webtoon.service;

import com.review.webtoon.entity.Member;
import com.review.webtoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long saveUser(Member member){
        return memberRepository.save(member).getId();
    }
    public Optional<Member> findUserByEntityManager(String username){
        return memberRepository.findMemberEntityGraphByUsername(username);

    }
}
