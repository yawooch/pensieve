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
    private int       memoryId;//�޸𸮾��̵�
    private Date strDate; //��������
    private Date endDate; //��������
    private Date succDate;//��������
}
