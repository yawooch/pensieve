package com.pjt.pensieve.jy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemoController {

	@RequestMapping(value = "/jy/memo")
    public ModelAndView jyMemo(ModelAndView modelAndView)
    {
        modelAndView.setViewName("jy/memo");
        return modelAndView;
    }
	
}
