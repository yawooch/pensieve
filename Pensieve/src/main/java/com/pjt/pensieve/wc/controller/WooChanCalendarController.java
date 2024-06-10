package com.pjt.pensieve.wc.controller;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.wc.api.Astronomy24DateResponse;
import com.pjt.pensieve.wc.api.AstronomyDateResponse;
import com.pjt.pensieve.wc.api.SpecialDateApiClient;
import com.pjt.pensieve.wc.api.SpecialDateResponse;
import com.pjt.pensieve.wc.model.service.MemoryCalendarService;
import com.pjt.pensieve.wc.model.service.MemoryService;
import com.pjt.pensieve.wc.model.vo.Event;
import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.SpecialDate;

import lombok.RequiredArgsConstructor;

@Controller

@RequestMapping("/wc")
@RequiredArgsConstructor
public class WooChanCalendarController
{
    private final SpecialDateApiClient specialDateApiClient;
    private final MemoryCalendarService memoryCalendarService;
    private final MemoryService memoryservice;
    
    @RequestMapping(value = "/calendar")
    public ModelAndView calendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/calendar");
        return modelAndView;
    }

    @PostMapping("/calendar/getCalendarEvent")
    public ResponseEntity<Map<String,Object>> getCalendarEvent(@RequestBody(required=false) String request)
    {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        List<SpecialDate> specialDates = memoryCalendarService.getCommonDays();
        
        List<Map<String, Object>> calendarDates = new ArrayList<Map<String,Object>>();

        DateTimeFormatter beforePattern = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter afterPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (SpecialDate specialDate : specialDates)
        {
            Map<String, Object> calendarDate = new HashMap<String, Object>();
            
            calendarDate.put("start", LocalDate.parse(specialDate.getLocdate(), beforePattern).format(afterPattern));
            calendarDate.put("title", specialDate.getDateName());
            calendarDate.put("editable", false);
            if(specialDate.getIsHoliday().equals("Y"))
            {
                calendarDate.put("backgroundColor" , "rgba(255, 62, 62, 0.2)");
                calendarDate.put("textColor" , "red");
                calendarDate.put("display", "background");
            }
            else
            {
                calendarDate.put("color" , "#20c997");
            }
            calendarDates.add(calendarDate);
        }
        

        List<Event> events = memoryCalendarService.getEvents();
        
        
        resultMap.put("events", calendarDates);
        resultMap.put("memories", events);
        
        return ResponseEntity.ok(resultMap);
    }
    
    @PostMapping("/calendar/calendarDelete")
    public ModelAndView memoryDelete(ModelAndView modelAndView, @RequestParam("memoryId") int memoryId)
    {
        int result = 0;
        
        Memory memory = memoryservice.getMemory(memoryId);
        
        result = memoryservice.deleteMemory(memoryId);
        
        if(memory.getTodoYn().equals("Y"))
        {
            result += memoryservice.deleteTodo(memoryId);
        }
        else
        {
            result += memoryCalendarService.deleteSchedule(memoryId);
        }
        
        modelAndView.addObject("result", result);
        modelAndView.setViewName("/wc/text");
        return modelAndView;
    }    
    
    @ResponseBody
    @PostMapping("/calendar/saveSpecialDays")
    public Map<String,Object> saveSpecialDays(@RequestBody String request) throws RestClientException, URISyntaxException
    {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        int result = 0;
        
        String solYear = "2025";
        
        SpecialDateResponse holiDeInfo          = specialDateApiClient.getHoliDeInfo(solYear);
        SpecialDateResponse restDeInfo          = specialDateApiClient.getRestDeInfo(solYear);
        SpecialDateResponse anniversaryInfo     = specialDateApiClient.getAnniversaryInfo(solYear);
        AstronomyDateResponse sundryDayInfo     = specialDateApiClient.getSundryDayInfo(solYear);
        Astronomy24DateResponse divisions24Info = specialDateApiClient.get24DivisionsInfo(solYear);
        
        System.out.println(holiDeInfo);
        System.out.println(restDeInfo);
        System.out.println(anniversaryInfo);
        System.out.println(sundryDayInfo);
        System.out.println(divisions24Info);
        
        result += memoryCalendarService.saveHolidayInfo(holiDeInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveHolidayInfo(restDeInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveHolidayInfo(anniversaryInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveAstronomyInfo(sundryDayInfo.getAstronomyDateItems()); 
        result += memoryCalendarService.saveAstronomyInfo(divisions24Info.getAstronomyDateItems()); 
        
        resultMap.put("holiDeInfoResponse", holiDeInfo);
        resultMap.put("restDeInfoResponse", restDeInfo);
        resultMap.put("anniversaryInfoResponse", anniversaryInfo);
        resultMap.put("anniversaryInfoResponse", sundryDayInfo);
        resultMap.put("divisions24InfoResponse", divisions24Info);
        resultMap.put("result", result);
        
        return resultMap;
    }
}
