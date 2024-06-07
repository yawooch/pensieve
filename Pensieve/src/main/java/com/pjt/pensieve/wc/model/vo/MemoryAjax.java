package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryAjax
{
    private String memoryId;   //�޸𸮾��̵� 
    private String content;    //����
    private String contentOrig;//�������
    private String title;      //����
    private String createDate; //��������
    private String modifyDate; //��������
    private String category;   //ī�װ�
    private String memberId;   //����ھ��̵�
    private String todoYn;     //�ؾ����Ͽ���
    private String showDiv;    //�����ֱⱸ��
    private String strDate;   //��������
    private String endDate;   //��������
    private String succDate;  //��������
    private String repeatPeriod;  //�ݺ��Ⱓ
}
