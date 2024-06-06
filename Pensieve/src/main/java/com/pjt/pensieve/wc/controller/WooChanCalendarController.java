package com.pjt.pensieve.wc.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.wc.api.Astronomy24DateResponse;
import com.pjt.pensieve.wc.api.AstronomyDateResponse;
import com.pjt.pensieve.wc.api.SpecialDateApiClient;
import com.pjt.pensieve.wc.api.SpecialDateResponse;
import com.pjt.pensieve.wc.model.service.MemoryCalendarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/wc")
@RequiredArgsConstructor
public class WooChanCalendarController
{
    private final SpecialDateApiClient specialDateApiClient;
    private final MemoryCalendarService memoryCalendarService;
    
    @RequestMapping(value = "/calendar")
    public ModelAndView calendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/calendar");
        return modelAndView;
    }
    

    @PostMapping(value = "/calendar/getCalendarEvent")
    public ResponseEntity<Map<String,Object>> getCalendarEvent(@RequestBody String request)
    {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        
        
        
        return ResponseEntity.ok(resultMap);
    }
    
    
    @ResponseBody
    @PostMapping(value = "/calendar/saveSpecialDays")
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
