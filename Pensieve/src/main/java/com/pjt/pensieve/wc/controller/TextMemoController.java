package com.pjt.pensieve.wc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.main.common.PageInfo;
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

        memory = memoryservice.getMemory(memory.getMemoryId());
        
        Parser parser = Parser.builder().build();
        Node document = parser.parse(memory.getContent());
        HtmlRenderer rederer = HtmlRenderer.builder().build();
        String content = rederer.render(document);
        memory.setContent(content);
        
        log.info("content : ", memory.getContent());
        log.info("title   : ", memory.getTitle());
        log.info("todoYn  : ", memory.getTodoYn());
        
        map.put("resultCode", result);
        map.put("memory"    , memory);
        return ResponseEntity.ok(map);
    }
    
    @GetMapping("wc/memoryDelete")
    public ModelAndView memoryDelete(ModelAndView modelAndView, @RequestParam("memoryId") int memoryId)
    {
        
        log.info("memoryId");

        modelAndView.setViewName("/wc/text");
        
        return modelAndView;
    }
    @PostMapping("/wc/text/memorySelect")
    public ResponseEntity<Map<String, Object>> memorySelect(@RequestParam("currPage") int currPage)
    {
        System.out.println("currPage :" + currPage);
        
        
        Map<String, Object> resultMap = new HashMap<>();
        
        // 전체 게시물 수 조회
        int listCount = memoryservice.getMemoryCount();

        System.out.println("listCount :" + listCount);
        
        // 페이징처리
        PageInfo pageInfo = new PageInfo(currPage, 5, listCount, 3);

        List<Memory> memories = new ArrayList<Memory>();
        
        memories = memoryservice.getMemories(pageInfo);
        
        System.out.println(memories);
        
        for (Memory memory : memories)
        {
            //content를 html 형식으로 바꾸어주는 commonMark API
            Parser parser = Parser.builder().build();
            Node document = parser.parse(memory.getContent());
            HtmlRenderer rederer = HtmlRenderer.builder().build();
            String content = rederer.render(document);
            memory.setContent(content);
        }
        
        resultMap.put("totalMemories", listCount);
        resultMap.put("memories"     , memories);
        return ResponseEntity.ok(resultMap);
    }
}
