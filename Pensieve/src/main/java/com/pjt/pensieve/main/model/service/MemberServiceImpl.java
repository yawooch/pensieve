package com.pjt.pensieve.main.model.service;

import org.springframework.stereotype.Service;

import com.pjt.pensieve.main.model.mapper.MemberMapper;
import com.pjt.pensieve.main.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService
{
    private final MemberMapper mapper;
    @Override
    public Member checkMemberId(String memberId)
    {
        return mapper.selectMemberById(memberId);
    }

}
