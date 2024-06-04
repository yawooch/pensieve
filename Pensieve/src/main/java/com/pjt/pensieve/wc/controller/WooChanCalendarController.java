package com.pjt.pensieve.wc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/wc")
@RequiredArgsConstructor
public class WooChanCalendarController
{
    @RequestMapping(value = "/calendar")
    public ModelAndView calendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/calendar");
        return modelAndView;
    }
    

    @GetMapping(value = "/calendar/getCalendarEvent")
    public ResponseEntity<Map<String,Object>> getCalendarEvent(@RequestBody Map<String,Object> requestMap)
    {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        System.out.println(requestMap);
        
        
        
        
        return ResponseEntity.ok(resultMap);
    }
}
