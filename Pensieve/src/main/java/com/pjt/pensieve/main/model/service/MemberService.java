package com.pjt.pensieve.main.model.service;

import com.pjt.pensieve.main.model.vo.Member;

public interface MemberService
{

    Member checkMemberId(String memberId,String memberPw);

    int saveMember(Member requestMember);
    
    boolean isDuplicated(String memberId);

}
