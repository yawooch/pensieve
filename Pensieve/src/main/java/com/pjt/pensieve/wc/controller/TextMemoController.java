package com.pjt.pensieve.wc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.pjt.pensieve.wc.model.vo.Todo;

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

    @PostMapping("/wc/text/memorySave")
    public ResponseEntity<Map<String, Object>> memorySave(@RequestBody Memory memory)
    {
        int result = 0;
        Map<String, Object> map = new HashMap<>();

        //여기서 null이 들어가면 insert 될때 null을 넣을수 없음 에러발생
        memory.setTodoYn(memory.getTodoYn()==null?"N":"Y");

        result = memoryservice.saveMemory(memory);
        
        Todo toDo = new Todo();
        //memoryId 가 생성된 후에 Todo VO를 insert한다.
        if(memory.getTodoYn().equals("Y"))
        {
            toDo.setMemoryId(memory.getMemoryId());
            result = memoryservice.saveTodo(toDo);
        }
        //Todo VO가 delete가 되는 경우 (수정했는데 todoYn을 "N"으로 수정)
        if(memory.getTodoYn().equals("N")&&memory.getMemoryId()!=0)
        {
            result = memoryservice.deleteTodo(memory.getMemoryId());
        }

        memory = memoryservice.getMemory(memory.getMemoryId());
        memory.setTodo(toDo);//키를갖고 왔을땐 Todo Vo가 없으므로 넣어준다.
        
        Parser parser = Parser.builder().build();
        Node document = parser.parse(memory.getContent());
        HtmlRenderer rederer = HtmlRenderer.builder().build();
        String content = rederer.render(document);
        memory.setContent(content);
        
        map.put("resultCode", result);
        map.put("memory"    , memory);
        return ResponseEntity.ok(map);
    }
    
    @GetMapping("wc/text/memoryDelete")
    public ModelAndView memoryDelete(ModelAndView modelAndView, @RequestParam("memoryId") int memoryId)
    {
        int result = 0;
        
        System.out.println("memoryId : " + memoryId);

        result = memoryservice.deleteMemory(memoryId);
        result = memoryservice.deleteTodo(memoryId);
        
        modelAndView.addObject("result", result);
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

    @PostMapping("/wc/text/checkTodo")
    public ResponseEntity<Map<String, Object>> checkTodo(@RequestBody Map<String, Object> request)
    {
        int result = 0;
        Map<String, Object> map = new HashMap<>();

        int memoryId = Integer.parseInt(request.get("memoryId").toString()); 
        boolean succDateBool = (boolean)request.get("succDateBool"); 
        LocalDateTime succDate = succDateBool?LocalDateTime.now():null;
        
        result = memoryservice.checkTodo(memoryId, succDate);

        map.put("resultCode", result);
        map.put("memoryId"    , memoryId);
        map.put("succDate"    , (succDate==null?null:succDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        return ResponseEntity.ok(map);
    }
}
