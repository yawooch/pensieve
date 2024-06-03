package com.pjt.pensieve.yh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class YeonheeController
{


    @RequestMapping(value = "/yh")
    public ModelAndView yhFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yh/home");
        return modelAndView;
    }

    @RequestMapping(value = "/yh/calendar")
    public ModelAndView yhCalendar(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yh/calendar");
        return modelAndView;
    }
    
    @RequestMapping(value = "/yh/timeline")
    public ModelAndView yhTimeline(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yh/timeline");
        return modelAndView;
    }
}