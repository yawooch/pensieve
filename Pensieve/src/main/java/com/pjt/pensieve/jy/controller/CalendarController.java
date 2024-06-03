package com.pjt.pensieve.jy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {
	
	@RequestMapping(value = "/jy/calendar")
    public ModelAndView jyCalendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("jy/calendar");
        return modelAndView;
    }
	
}
