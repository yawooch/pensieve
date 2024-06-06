package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDate
{
    private int    dateId;      //특일아이디
    private String dateKind;    //종류
    private String dateName;    //명칭
    private String isHoliday;   //휴일여부
    private String kst;         //한국표준시간
    private String locdate;     //날짜
    private String seq;         //순번
    private String sunLongitude;//태양황경도
}
