package com.pjt.pensieve.wc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AstronomyDateItem
{
    private String dateKind;    //����
    private String dateName;    //��Ī
    private String isHoliday;   //���Ͽ���
    private String kst;         //�ѱ�ǥ�ؽð�
    private int    locdate;     //��¥
    private int    seq;         //����
    private int    sunLongitude;//�¾�Ȳ�浵
}
