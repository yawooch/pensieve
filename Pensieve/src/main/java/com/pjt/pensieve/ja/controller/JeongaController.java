package com.pjt.pensieve.ja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/ja")
public class JeongaController
{
    @GetMapping("/text")
    public ModelAndView ywFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("ja/home");
        return modelAndView;
    }

    @GetMapping(value = "/calendar")
    public ModelAndView calendar(ModelAndView modelAndView)
    {
        modelAndView.setViewName("ja/calendar");
        return modelAndView;
    }
}
