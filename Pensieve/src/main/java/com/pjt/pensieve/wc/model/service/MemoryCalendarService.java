package com.pjt.pensieve.wc.model.service;

import java.util.List;

import com.pjt.pensieve.wc.api.AstronomyDateItem;
import com.pjt.pensieve.wc.api.SpecialDateItem;
import com.pjt.pensieve.wc.model.vo.Event;
import com.pjt.pensieve.wc.model.vo.Schedule;
import com.pjt.pensieve.wc.model.vo.SpecialDate;

public interface MemoryCalendarService
{

    int saveHolidayInfo(List<SpecialDateItem> specialDateItems);

    int saveAstronomyInfo(List<AstronomyDateItem> astronomyDateItems);

    List<SpecialDate> getCommonDays();

    int deleteSchedule(int memoryId);

    int saveSchedule(Schedule schedule);

    Event getEvent(int memoryId);

    List<Event> getEvents();

}
