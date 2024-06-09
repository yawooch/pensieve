package com.pjt.pensieve.main.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt.pensieve.main.model.vo.Member;

@Mapper
public interface MemberMapper {

	int selectCount();

    Member selectMemberById(@Param("memberId") String memberId);

}