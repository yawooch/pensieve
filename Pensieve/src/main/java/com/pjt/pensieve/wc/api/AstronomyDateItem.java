package com.pjt.pensieve.wc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AstronomyDateItem
{
    private String dateKind;    //종류
    private String dateName;    //명칭
    private String isHoliday;   //휴일여부
    private String kst;         //한국표준시간
    private int    locdate;     //날짜
    private int    seq;         //순번
    private int    sunLongitude;//태양황경도
}
