package com.pjt.pensieve.wc.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.pensieve.wc.model.vo.Event;
import com.pjt.pensieve.wc.model.vo.Schedule;
import com.pjt.pensieve.wc.model.vo.SpecialDate;

@Mapper
public interface CalendarMapper
{
    int insertSpecialDateHolidays(SpecialDate specialDate);

    List<SpecialDate> selectCommonDays();

    int deleteSchedule(int memoryId);

    int insertSchedule(Schedule schedule);

    Event selectEvent(int memoryId);

    List<Event> selectEvents();
}
