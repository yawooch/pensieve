package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule
{
    private int    memoryId;   //�޸𸮾��̵�
    private String strDate;    //��������
    private String endDate;    //��������
    private String repeatPriod;//�ݺ��Ⱓ
}
