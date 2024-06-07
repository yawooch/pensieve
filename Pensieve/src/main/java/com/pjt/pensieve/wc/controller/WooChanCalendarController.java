package com.pjt.pensieve.wc.controller;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.wc.api.Astronomy24DateResponse;
import com.pjt.pensieve.wc.api.AstronomyDateResponse;
import com.pjt.pensieve.wc.api.SpecialDateApiClient;
import com.pjt.pensieve.wc.api.SpecialDateResponse;
import com.pjt.pensieve.wc.model.service.MemoryCalendarService;
import com.pjt.pensieve.wc.model.service.MemoryService;
import com.pjt.pensieve.wc.model.vo.Event;
import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.MemoryAjax;
import com.pjt.pensieve.wc.model.vo.Schedule;
import com.pjt.pensieve.wc.model.vo.SpecialDate;
import com.pjt.pensieve.wc.model.vo.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller

@Slf4j
@RequestMapping("/wc")
@RequiredArgsConstructor
public class WooChanCalendarController
{
    private final SpecialDateApiClient specialDateApiClient;
    private final MemoryCalendarService memoryCalendarService;
    private final MemoryService memoryservice;
    
    @RequestMapping(value = "/calendar")
    public ModelAndView calendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/calendar");
        return modelAndView;
    }

    @PostMapping("/calendar/getCalendarEvent")
    public ResponseEntity<Map<String,Object>> getCalendarEvent(@RequestBody(required=false) String request)
    {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        List<SpecialDate> specialDates = memoryCalendarService.getCommonDays();
        
        List<Map<String, Object>> calendarDates = new ArrayList<Map<String,Object>>();

        DateTimeFormatter beforePattern = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter afterPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (SpecialDate specialDate : specialDates)
        {
            Map<String, Object> calendarDate = new HashMap<String, Object>();
            
            calendarDate.put("start", LocalDate.parse(specialDate.getLocdate(), beforePattern).format(afterPattern));
            calendarDate.put("title", specialDate.getDateName());
            calendarDate.put("editable", false);
            if(specialDate.getIsHoliday().equals("Y"))
            {
                calendarDate.put("backgroundColor" , "rgba(255, 62, 62, 0.2)");
                calendarDate.put("textColor" , "red");
                calendarDate.put("display", "background");
            }
            else
            {
                calendarDate.put("color" , "#20c997");
            }
            calendarDates.add(calendarDate);
        }
        

        List<Event> events = memoryCalendarService.getEvents();
        
        
        resultMap.put("events", calendarDates);
        resultMap.put("memories", events);
        
        return ResponseEntity.ok(resultMap);
    }
    
    @PostMapping("/calendar/calendarSave")
    public ResponseEntity<Map<String,Object>> calendarSave(@RequestBody MemoryAjax requestMemory)
    {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int result = 0;
        
        log.info("request : {}", requestMemory);

        Memory memory = new Memory();
        
        memory.setMemoryId(   requestMemory.getMemoryId().equals("0")?0 :Integer.parseInt(requestMemory.getMemoryId())) ;
        memory.setContent(    requestMemory.getContent() ==null?"":requestMemory.getContent());
        memory.setContentOrig(requestMemory.getContent() ==null?"":requestMemory.getContent());
        memory.setTitle(      requestMemory.getTitle()   ==null?"":requestMemory.getTitle());
        memory.setCategory(   requestMemory.getCategory()==null?"":requestMemory.getCategory());
        memory.setMemberId(null);
        
        //여기서 null이 들어가면 insert 될때 null을 넣을수 없음 에러발생
        memory.setTodoYn(requestMemory.getTodoYn()==null?"N":"Y");
        result = memoryservice.saveMemory(memory);

        DateTimeFormatter formatter     = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH24:mm:ss");
        Todo         toDo = new Todo();
        Schedule schedule = new Schedule();
        
        
        //memoryId 가 생성된 후에 Todo VO를 insert한다.
        if(memory.getTodoYn().equals("Y"))
        {
            if(memory.getMemoryId() != 0)
            {
                result = memoryservice.deleteTodo(memory.getMemoryId());
            }
            
            toDo.setMemoryId(memory.getMemoryId());
            
            Date strDate = null;
            Date endDate = null;
            if(!requestMemory.getStrDate().equals(""))
            {
                String strDateObj = requestMemory.getStrDate().toString();
                DateTimeFormatter useFormatter = strDateObj.length() > 10?longFormatter:formatter;
                strDate = java.sql.Date.valueOf(LocalDate.parse(strDateObj,useFormatter));
            }
            if(!requestMemory.getEndDate().equals(""))
            {
                String endDateObj = requestMemory.getEndDate().toString();
                DateTimeFormatter useFormatter = endDateObj.length() > 10?longFormatter:formatter;
                endDate = java.sql.Date.valueOf(LocalDate.parse(endDateObj,useFormatter));
            }
            
            //LocalDate를 Date로 변환하는 과정(Date를 바로쓰면 try 쓰기 귀찮아서...
            toDo.setStrDate(strDate);
            toDo.setEndDate(endDate);
            
            result = memoryservice.saveTodo(toDo);
        }
        //Todo 가 아니면 SCHEDULE에 insert 한다.
        else
        {
            if(memory.getMemoryId() != 0)
            {
                result = memoryCalendarService.deleteSchedule(memory.getMemoryId());
            }
            
            schedule.setMemoryId(memory.getMemoryId());
            schedule.setStrDate(requestMemory.getStrDate());
            schedule.setEndDate(requestMemory.getEndDate());
            schedule.setRepeatPriod(requestMemory.getRepeatPeriod());
            
            result = memoryCalendarService.saveSchedule(schedule);
        }

        Event event = memoryCalendarService.getEvent(memory.getMemoryId());

        resultMap.put("result", result);
        resultMap.put("event", event);
        
        return ResponseEntity.ok(resultMap);
    }
    
    @PostMapping("/calendar/calendarDelete")
    public ModelAndView memoryDelete(ModelAndView modelAndView, @RequestParam("memoryId") int memoryId)
    {
        int result = 0;
        
        Memory memory = memoryservice.getMemory(memoryId);
        
        result = memoryservice.deleteMemory(memoryId);
        
        if(memory.getTodoYn().equals("Y"))
        {
            result += memoryservice.deleteTodo(memoryId);
        }
        else
        {
            result += memoryCalendarService.deleteSchedule(memoryId);
        }
        
        modelAndView.addObject("result", result);
        modelAndView.setViewName("/wc/text");
        return modelAndView;
    }    
    
    @ResponseBody
    @PostMapping("/calendar/saveSpecialDays")
    public Map<String,Object> saveSpecialDays(@RequestBody String request) throws RestClientException, URISyntaxException
    {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        int result = 0;
        
        String solYear = "2025";
        
        SpecialDateResponse holiDeInfo          = specialDateApiClient.getHoliDeInfo(solYear);
        SpecialDateResponse restDeInfo          = specialDateApiClient.getRestDeInfo(solYear);
        SpecialDateResponse anniversaryInfo     = specialDateApiClient.getAnniversaryInfo(solYear);
        AstronomyDateResponse sundryDayInfo     = specialDateApiClient.getSundryDayInfo(solYear);
        Astronomy24DateResponse divisions24Info = specialDateApiClient.get24DivisionsInfo(solYear);
        
        System.out.println(holiDeInfo);
        System.out.println(restDeInfo);
        System.out.println(anniversaryInfo);
        System.out.println(sundryDayInfo);
        System.out.println(divisions24Info);
        
        result += memoryCalendarService.saveHolidayInfo(holiDeInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveHolidayInfo(restDeInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveHolidayInfo(anniversaryInfo.getSpecialDateItems()); 
        result += memoryCalendarService.saveAstronomyInfo(sundryDayInfo.getAstronomyDateItems()); 
        result += memoryCalendarService.saveAstronomyInfo(divisions24Info.getAstronomyDateItems()); 
        
        resultMap.put("holiDeInfoResponse", holiDeInfo);
        resultMap.put("restDeInfoResponse", restDeInfo);
        resultMap.put("anniversaryInfoResponse", anniversaryInfo);
        resultMap.put("anniversaryInfoResponse", sundryDayInfo);
        resultMap.put("divisions24InfoResponse", divisions24Info);
        resultMap.put("result", result);
        
        return resultMap;
    }
}
