package com.pjt.pensieve.wc.model.service;

import java.util.List;

import com.pjt.pensieve.wc.api.AstronomyDateItem;
import com.pjt.pensieve.wc.api.SpecialDateItem;

public interface MemoryCalendarService
{

    int saveHolidayInfo(List<SpecialDateItem> specialDateItems);

    int saveAstronomyInfo(List<AstronomyDateItem> astronomyDateItems);

}
