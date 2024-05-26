package com.pjt.pensieve.wc.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory
{
    private int           memoryId; 
    private String        content; 
    private String        contentOrig;
    private String        title;
    private Date          createDate;
    private Date          modifyDate;
    private String        category;
    private String        todoYn;
    private String        memberId;
}
