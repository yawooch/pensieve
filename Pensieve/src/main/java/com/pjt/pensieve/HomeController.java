package com.pjt.pensieve;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController
{

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model)
    {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "main/home";
    }

    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main/home");
        return modelAndView;
    }
    @RequestMapping(value = "/wc")
    public ModelAndView wcFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/home");
        return modelAndView;
    }
    @RequestMapping(value = "/yw")
    public ModelAndView ywFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yw/home");
        return modelAndView;
    }
    @RequestMapping(value = "/jy")
    public ModelAndView jyFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("jy/home");
        return modelAndView;
    }
    @RequestMapping(value = "/yh")
    public ModelAndView yhFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("yh/home");
        return modelAndView;
    }

    @RequestMapping(value = "/themePage")
    public ModelAndView themePage(ModelAndView modelAndView)
    {
        modelAndView.setViewName("themePage");
        return modelAndView;
    }
}
