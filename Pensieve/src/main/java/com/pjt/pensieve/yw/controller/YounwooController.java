package com.pjt.pensieve.yw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/yw")
public class YounwooController
{

    @GetMapping("/text")
    public ModelAndView ywFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yw/home");
        return modelAndView;
    }

    @GetMapping(value = "/calendar")
    public ModelAndView calendar(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yw/calendar");
        return modelAndView;
    }
}
