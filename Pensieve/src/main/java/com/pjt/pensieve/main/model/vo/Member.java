package com.pjt.pensieve.main.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member
{
    private int    memberNo;  //사용자가입일
    private String memberId;  //사용자번호
    private String memberPw;  //사용자아이디
    private String role;      //사용자아이디
    private Date   createDate;//사용자비밀번호
}
