package com.pjt.pensieve.yh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
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
}