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

    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login");
        return modelAndView;
    }
    @RequestMapping(value = "/login2")
    public ModelAndView login2(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login2");
        return modelAndView;
    }
    @RequestMapping(value = "/login3")
    public ModelAndView login3(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login3");
        return modelAndView;
    }
    @RequestMapping(value = "/login4")
    public ModelAndView login4(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login4");
        return modelAndView;
    }
    @RequestMapping(value = "/login5")
    public ModelAndView login5(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login5");
        return modelAndView;
    }
    @RequestMapping(value = "/login6")
    public ModelAndView login6(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/login6");
        return modelAndView;
    }
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main/home");
        return modelAndView;
    }
    @RequestMapping(value = "/main/calendar")
    public ModelAndView mainCalendar(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main/calendar");
        return modelAndView;
    }
    @RequestMapping(value = "/main/text")
    public ModelAndView mainText(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main/text");
        return modelAndView;
    }
    @RequestMapping(value = "/main/timeline")
    public ModelAndView mainTimeline(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main/timeline");
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
    @RequestMapping(value = "/template/themePage")
    public ModelAndView themePage(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/themePage");
        return modelAndView;
    }
    @RequestMapping(value = "/template/calendar1")
    public ModelAndView calendar1(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar1");
        return modelAndView;
    }
    @RequestMapping(value = "/template/calendar2")
    public ModelAndView calendar2(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar2");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar3")
    public ModelAndView calendar3(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar3");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar4")
    public ModelAndView calendar4(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar4");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar5")
    public ModelAndView calendar5(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar5");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar6")
    public ModelAndView calendar6(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar6");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar7")
    public ModelAndView calendar7(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar7");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar8")
    public ModelAndView calendar8(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar8");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar9")
    public ModelAndView calendar9(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar9");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar10")
    public ModelAndView calendar10(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar10");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar11")
    public ModelAndView calendar11(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar11");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar12")
    public ModelAndView calendar12(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar12");
        return modelAndView;
    }

    @RequestMapping(value = "/template/calendar13")
    public ModelAndView calendar13(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/calendar13");
        return modelAndView;
    }
    @RequestMapping(value = "/template/text")
    public ModelAndView templateText(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/text");
        return modelAndView;
    }
    @RequestMapping(value = "/template/timeline")
    public ModelAndView timeline(ModelAndView modelAndView)
    {
        modelAndView.setViewName("template/timeline");
        return modelAndView;
    }
    @RequestMapping(value = "/common/pageError")
    public ModelAndView pageError(ModelAndView modelAndView)
    {
        modelAndView.setViewName("common/pageError");
        return modelAndView;
    }
    
}