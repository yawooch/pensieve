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
    private int    memberNo;  //����ڰ�����
    private String memberId;  //����ڹ�ȣ
    private String memberPw;  //����ھ��̵�
    private String role;      //����ھ��̵�
    private Date   createDate;//����ں�й�ȣ
}
