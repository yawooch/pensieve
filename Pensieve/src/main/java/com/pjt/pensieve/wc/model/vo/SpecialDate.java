package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDate
{
    private int    dateId;      //Ư�Ͼ��̵�
    private String dateKind;    //����
    private String dateName;    //��Ī
    private String isHoliday;   //���Ͽ���
    private String kst;         //�ѱ�ǥ�ؽð�
    private String locdate;     //��¥
    private String seq;         //����
    private String sunLongitude;//�¾�Ȳ�浵
}
