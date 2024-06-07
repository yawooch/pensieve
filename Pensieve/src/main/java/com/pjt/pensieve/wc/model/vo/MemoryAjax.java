package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryAjax
{
    private String memoryId;   //메모리아이디 
    private String content;    //내용
    private String contentOrig;//내용원본
    private String title;      //제목
    private String createDate; //생성일자
    private String modifyDate; //수정일자
    private String category;   //카테고리
    private String memberId;   //사용자아이디
    private String todoYn;     //해야할일여부
    private String showDiv;    //보여주기구분
    private String strDate;   //시작일자
    private String endDate;   //종료일자
    private String succDate;  //성공여부
    private String repeatPeriod;  //반복기간
}
