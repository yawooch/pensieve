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

        //���⼭ null�� ���� insert �ɶ� null�� ������ ���� �����߻�
        memory.setTodoYn(memory.getTodoYn()==null?"N":"Y");

        result = memoryservice.saveMemory(memory);
        
        Todo toDo = new Todo();
        //memoryId �� ������ �Ŀ� Todo VO�� insert�Ѵ�.
        if(memory.getTodoYn().equals("Y"))
        {
            toDo.setMemoryId(memory.getMemoryId());
            result = memoryservice.saveTodo(toDo);
        }
        //Todo VO�� delete�� �Ǵ� ��� (�����ߴµ� todoYn�� "N"���� ����)
        if(memory.getTodoYn().equals("N")&&memory.getMemoryId()!=0)
        {
            result = memoryservice.deleteTodo(memory.getMemoryId());
        }

        memory = memoryservice.getMemory(memory.getMemoryId());
        memory.setTodo(toDo);//Ű������ ������ Todo Vo�� �����Ƿ� �־��ش�.
        
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
        
        // ��ü �Խù� �� ��ȸ
        int listCount = memoryservice.getMemoryCount();

        System.out.println("listCount :" + listCount);
        
        // ����¡ó��
        PageInfo pageInfo = new PageInfo(currPage, 5, listCount, 3);

        List<Memory> memories = new ArrayList<Memory>();
        
        memories = memoryservice.getMemories(pageInfo);
        
        System.out.println(memories);
        
        for (Memory memory : memories)
        {
            //content�� html �������� �ٲپ��ִ� commonMark API
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
