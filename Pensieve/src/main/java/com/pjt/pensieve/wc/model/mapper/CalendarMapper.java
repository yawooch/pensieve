package com.pjt.pensieve.wc.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.pensieve.wc.model.vo.SpecialDate;

@Mapper
public interface CalendarMapper
{
    int insertSpecialDateHolidays(SpecialDate specialDate);
}
