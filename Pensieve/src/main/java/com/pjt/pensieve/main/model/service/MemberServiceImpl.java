package com.pjt.pensieve.main.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.main.model.mapper.MemberMapper;
import com.pjt.pensieve.main.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService
{
    private final MemberMapper mapper;
    private final BCryptPasswordEncoder encoder;
    @Override
    public Member checkMemberId(String memberId, String memberPw)
    {
        Member member = mapper.selectMemberById(memberId);
        
        System.out.println("Encoder Password : " + encoder.encode(memberPw));
        
        if (member == null || !encoder.matches(memberPw, member.getMemberPw()))
        {
            return null;
        }
        
        return member;
    }
    
    @Override
    @Transactional
    public int saveMember(Member requestMember)
    {
        if(requestMember.getMemberNo() == 0)
        {
            requestMember.setMemberPw(encoder.encode(requestMember.getMemberPw()));
        }
        
        return mapper.insertMember(requestMember);
    }


    @Override
    public boolean isDuplicated(String memberId)
    {
        return mapper.selectMemberById(memberId) != null;
    }
}
