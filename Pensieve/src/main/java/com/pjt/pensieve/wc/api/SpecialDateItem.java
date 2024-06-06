package com.pjt.pensieve.wc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDateItem
{
    private int    locdate;     //날짜
    private int    seq;         //순번
    private String dateKind;    //종류
    private String isHoliday;   //휴일여부
    private String dateName;    //명칭
}
