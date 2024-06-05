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
    private int    memoryFileId;    /*  메모리파일아이디      */
    private int    fileOwner;       /*  파일소유자            */
    private int    fileRelaMemoryId;/*  파일관계메모리아이디  */
    private String fileOrigName;    /*  파일원본이름          */
    private String fileReName;      /*  파일개명이름          */
    private Date   fileCreateDate;  /*  파일생성일자          */
    private Date   fileModifyDate;  /*  파일수정일자          */
}
