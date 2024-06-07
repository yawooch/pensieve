package com.pjt.pensieve.wc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event
{
    private int    memoryId;
    private String strDate;
    private String endDate;
    private String succDate;
    private String repeatPriod;
    private String title;
    private String category;
    private String content;
    private String todoYn;
}
