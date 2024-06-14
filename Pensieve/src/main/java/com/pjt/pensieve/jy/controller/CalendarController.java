package com.pjt.pensieve.jy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.jy.model.service.CalendarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/jy")
public class CalendarController {
	private final CalendarService service;
	
	@GetMapping("/calendar")
    public ModelAndView jyCalendarView(ModelAndView modelAndView){
		modelAndView.setViewName("jy/calendar");
		return modelAndView;
	}
	
	@PostMapping("/calendarSave")
	public ModelAndView jyCalendarSave(ModelAndView modelAndView){
		int result = 0;
		
		return modelAndView;
	}

	
	
	
}
