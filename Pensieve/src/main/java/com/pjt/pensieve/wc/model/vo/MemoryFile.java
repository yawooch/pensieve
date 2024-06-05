package com.pjt.pensieve.wc.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryFile
{
    private int    memoryFileId;    /*  �޸����Ͼ��̵�      */
    private int    fileOwner;       /*  ���ϼ�����            */
    private int    fileRelaMemoryId;/*  ���ϰ���޸𸮾��̵�  */
    private String fileOrigName;    /*  ���Ͽ����̸�          */
    private String fileReName;      /*  ���ϰ����̸�          */
    private Date   fileCreateDate;  /*  ���ϻ�������          */
    private Date   fileModifyDate;  /*  ���ϼ�������          */
}
