package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory
{
    private String memoryId; 
    private String content;
    private String title;
    private String createDate;
    private String modifyDate;
    private String category;
    private String todoYn;
    private String memberNo;
}
