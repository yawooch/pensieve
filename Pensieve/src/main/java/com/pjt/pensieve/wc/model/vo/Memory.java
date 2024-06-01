package com.pjt.pensieve.wc.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory
{
    private int    memoryId;   //메모리아이디 
    private String content;    //내용
    private String contentOrig;//내용원본
    private String title;      //제목
    private Date   createDate; //생성일자
    private Date   modifyDate; //수정일자
    private String category;   //카테고리
    private String memberId;   //사용자아이디
    private String todoYn;     //해야할일여부
    private String showDiv;    //보여주기구분
    private Todo   todo;       //해야할일상세정보
}
