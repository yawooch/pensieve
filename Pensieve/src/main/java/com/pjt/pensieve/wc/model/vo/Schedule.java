package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule
{
    private int    memoryId;   //메모리아이디
    private String strDate;    //시작일자
    private String endDate;    //종료일자
    private String repeatPriod;//반복기간
}
