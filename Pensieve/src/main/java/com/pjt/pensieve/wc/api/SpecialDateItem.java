package com.pjt.pensieve.wc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDateItem
{
    private int    locdate;     //��¥
    private int    seq;         //����
    private String dateKind;    //����
    private String isHoliday;   //���Ͽ���
    private String dateName;    //��Ī
}
