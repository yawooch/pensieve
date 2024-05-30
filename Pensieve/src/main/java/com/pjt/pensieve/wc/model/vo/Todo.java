package com.pjt.pensieve.wc.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo
{
    private int       memoryId;//메모리아이디
    private Date strDate; //시작일자
    private Date endDate; //종료일자
    private Date succDate;//성공여부
}
