package com.pjt.pensieve.wc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.wc.api.AstronomyDateItem;
import com.pjt.pensieve.wc.api.SpecialDateItem;
import com.pjt.pensieve.wc.model.mapper.CalendarMapper;
import com.pjt.pensieve.wc.model.vo.Event;
import com.pjt.pensieve.wc.model.vo.MemoryAjax;
import com.pjt.pensieve.wc.model.vo.Schedule;
import com.pjt.pensieve.wc.model.vo.SpecialDate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryCalendarServiceImpl implements MemoryCalendarService
{
    private final CalendarMapper mapper;
    
    @Override
    @Transactional
    public int saveHolidayInfo(List<SpecialDateItem> specialDateItems)
    {
        int result = 0;

        for (SpecialDateItem specialDateItem : specialDateItems)
        {
            SpecialDate specialDate = new SpecialDate(); 
            specialDate.setDateKind(specialDateItem.getDateKind());
            specialDate.setDateName(specialDateItem.getDateName());
            specialDate.setIsHoliday(specialDateItem.getIsHoliday());
            specialDate.setLocdate(Integer.toString(specialDateItem.getLocdate()));
            specialDate.setSeq(Integer.toString(specialDateItem.getSeq()));
            
            result += mapper.insertSpecialDateHolidays(specialDate);
        }
        
        return result;
    }
    @Override
    @Transactional
    public int saveAstronomyInfo(List<AstronomyDateItem> astronomyDateItems)
    {
        int result = 0;

        for (AstronomyDateItem astronomyDateItem : astronomyDateItems)
        {
            SpecialDate specialDate = new SpecialDate(); 
            specialDate.setDateKind(astronomyDateItem.getDateKind());
            specialDate.setDateName(astronomyDateItem.getDateName());
            specialDate.setIsHoliday(astronomyDateItem.getIsHoliday());
            specialDate.setLocdate(Integer.toString(astronomyDateItem.getLocdate()));
            specialDate.setSeq(Integer.toString(astronomyDateItem.getSeq()));
            specialDate.setSunLongitude(Integer.toString(astronomyDateItem.getSunLongitude()));
            specialDate.setKst(astronomyDateItem.getKst());
            
            result += mapper.insertSpecialDateHolidays(specialDate);
        }
        
        return result;
    }
    @Override
    public List<SpecialDate> getCommonDays()
    {
        return mapper.selectCommonDays();
    }
    
    @Override
    @Transactional
    public int deleteSchedule(int memoryId)
    {
        return mapper.deleteSchedule(memoryId);
    }
    @Override
    @Transactional
    public int saveSchedule(MemoryAjax requestMemory)
    {
        Schedule schedule = new Schedule();

        schedule.setMemoryId(   requestMemory.getMemoryId().equals("")?0 :Integer.parseInt(requestMemory.getMemoryId())) ;
        schedule.setStrDate(requestMemory.getStrDate());
        schedule.setEndDate(requestMemory.getEndDate());
        schedule.setRepeatPriod(requestMemory.getRepeatPeriod());
        
        if(schedule.getMemoryId() != 0)
        {
            mapper.deleteSchedule(schedule.getMemoryId());
        }
        
        return mapper.insertSchedule(schedule);
    }
    @Override
    public Event getEvent(int memoryId)
    {
        return mapper.selectEvent(memoryId);
    }
    @Override
    public List<Event> getEvents()
    {
        return mapper.selectEvents();
    }

}
