package com.pjt.pensieve.jy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {
    @RequestMapping(value = "/jy/todo")
    public ModelAndView jyTodoView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("jy/todo");
        return modelAndView;
    }
}
