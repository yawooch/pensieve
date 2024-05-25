package com.pjt.pensieve.wc.controller;

import java.util.HashMap;
import java.util.Map;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.markdown.MarkdownRenderer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.wc.model.service.MemoryService;
import com.pjt.pensieve.wc.model.vo.Memory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TextMemoController
{
    private final MemoryService memoryservice;
    
    @RequestMapping(value = "/wc/text")
    public ModelAndView wcFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/text");
        return modelAndView;
    }
    @RequestMapping(value = "/wc/calendar")
    public ModelAndView calendarView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/calendar");
        return modelAndView;
    }
    @RequestMapping(value = "/wc/timeline")
    public ModelAndView timelineView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/timeline");
        return modelAndView;
    }
    
    @PostMapping("/wc/memoryInsert")
    public ResponseEntity<Map<String, Object>> memoryInsert(@RequestBody Memory memory)
    {
        int result = 0;
        Map<String, Object> map = new HashMap<>();

        System.out.println("memory.getContent() : " + memory.getContent());
        
        memory.setTodoYn((memory.getTodoYn()==null?"N":"Y"));

        log.info("result : ", result);
        result = memoryservice.save(memory);
        log.info("memory.getContent() : ", memory.getContent());

        memory = memoryservice.getMemoryOne(memory.getMemoryId());
        
        Parser parser = Parser.builder().build();
        Node document = parser.parse(memory.getContent());
        HtmlRenderer rederer = HtmlRenderer.builder().build();
        String content = rederer.render(document);
        memory.setContent(content);
        
//        String content = MarkdownRenderer
        
        log.info("content : ", memory.getContent());
        log.info("title   : ", memory.getTitle());
        log.info("todoYn  : ", memory.getTodoYn());
        
        map.put("resultCode", result);
        map.put("memory"    , memory);
        return ResponseEntity.ok(map);
    }
}
