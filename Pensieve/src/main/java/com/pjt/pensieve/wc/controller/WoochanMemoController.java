package com.pjt.pensieve.wc.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.service.MemoryFileService;
import com.pjt.pensieve.wc.model.service.MemoryService;
import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.MemoryAjax;
import com.pjt.pensieve.wc.model.vo.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/wc")
@RequiredArgsConstructor
public class WoochanMemoController
{
    private final MemoryService     memoryservice;
    private final MemoryFileService memoryFileService;
    
    @RequestMapping(value = "/timeline")
    public ModelAndView timelineView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/timeline");
        return modelAndView;
    }
    @RequestMapping(value = "/text")
    public ModelAndView wcFirstView(ModelAndView modelAndView)
    {
        modelAndView.setViewName("wc/text");
        return modelAndView;
    }

    @PostMapping("/text/memorySave")
    public ResponseEntity<Map<String, Object>> memorySave(MemoryAjax requestMemory, @RequestParam(value="imageFile", required=false) List<MultipartFile> imageFiles)
    {
        int result = 0;
        Memory memory = new Memory();
        Map<String, Object> map = new HashMap<>();
        
        memory.setMemoryId(   requestMemory.getMemoryId().equals("")?0 :Integer.parseInt(requestMemory.getMemoryId())) ;
        memory.setContent(    requestMemory.getContent() ==null?"":requestMemory.getContent());
        memory.setContentOrig(requestMemory.getContent() ==null?"":requestMemory.getContent());
        memory.setTitle(      requestMemory.getTitle()   ==null?"":requestMemory.getTitle());
        memory.setCategory(   requestMemory.getCategory()==null?"":requestMemory.getCategory());
        memory.setMemberId(null);
        
        //여기서 null이 들어가면 insert 될때 null을 넣을수 없음 에러발생
        memory.setTodoYn(requestMemory.getTodoYn()==null?"N":"Y");
        
        result = memoryservice.saveMemory(memory);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Todo toDo = new Todo();
        //memoryId 가 생성된 후에 Todo VO를 insert한다.
        if(memory.getTodoYn().equals("Y"))
        {
            result = memoryservice.deleteTodo(memory.getMemoryId());
            
            toDo.setMemoryId(memory.getMemoryId());
            
            Date strDate = null;
            Date endDate = null;
            if(!requestMemory.getStrDate().equals(""))
            {
                Object strDateObj = requestMemory.getStrDate();
                LocalDate localStrDate = LocalDate.parse(strDateObj.toString(),formatter); 
                strDate = java.sql.Date.valueOf(localStrDate);
            }
            if(!requestMemory.getEndDate().equals(""))
            {
                Object endDateObj = requestMemory.getEndDate();
                LocalDate localEndDate = LocalDate.parse(endDateObj.toString(),formatter); 
                endDate = java.sql.Date.valueOf(localEndDate);
            }
            
            //LocalDate를 Date로 변환하는 과정(Date를 바로쓰면 try 쓰기 귀찮아서...
            toDo.setStrDate(strDate);
            toDo.setEndDate(endDate);
            
            result = memoryservice.saveTodo(toDo);
        }
        //Todo VO가 delete가 되는 경우 (수정했는데 todoYn을 "N"으로 수정)
        if(memory.getTodoYn().equals("N")&&memory.getMemoryId()!=0)
        {
            result = memoryservice.deleteTodo(memory.getMemoryId());
        }

        memory = memoryservice.getMemory(memory.getMemoryId());

        result = memoryFileService.saveFiles(imageFiles, memory.getMemoryId(), "resources/img/upload/wc/memo");
        
        Parser parser        = Parser.builder().build();
        Node document        = parser.parse(memory.getContent());
        HtmlRenderer rederer = HtmlRenderer.builder().build();
        String content       = rederer.render(document);
        memory.setContent(content);
        
        map.put("resultCode", result);
        map.put("memory"    , memory);
        return ResponseEntity.ok(map);
    }
    
    @GetMapping("/text/memoryDelete")
    public ModelAndView memoryDelete(ModelAndView modelAndView, @RequestParam("memoryId") int memoryId)
    {
        int result = 0;
        
        result = memoryservice.deleteMemory(memoryId);
        result = memoryservice.deleteTodo(memoryId);
        
        modelAndView.addObject("result", result);
        modelAndView.setViewName("/wc/text");
        return modelAndView;
    }
    
    @PostMapping("/text/memorySelect")
    public ResponseEntity<Map<String, Object>> memorySelect(@RequestParam("currPage") int currPage, @RequestParam("searchWord") String searchWord)
    {
        Map<String, Object> resultMap = new HashMap<>();

        // 전체 게시물 수 조회
        int listCount = memoryservice.getMemoryCount(searchWord);

        // 페이징처리
        PageInfo pageInfo = new PageInfo(currPage, 5, listCount, 6);

        List<Memory> memories = new ArrayList<Memory>();
        
        memories = memoryservice.getMemories(pageInfo, searchWord);
        
        for (Memory memory : memories)
        {
            //content를 html 형식으로 바꾸어주는 commonMark API
            Parser parser = Parser.builder().build();
            Node document = parser.parse(memory.getContent());
            HtmlRenderer rederer = HtmlRenderer.builder().build();
            String content = rederer.render(document);
            memory.setContent(content);
        }
        
        log.info("memories : {}", memories);
        
        resultMap.put("totalMemories", listCount);
        resultMap.put("memories"     , memories);
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/text/checkTodo")
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
