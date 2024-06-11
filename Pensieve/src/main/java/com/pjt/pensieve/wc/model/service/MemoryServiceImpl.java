package com.pjt.pensieve.wc.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.mapper.MemoryMapper;
import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.MemoryAjax;
import com.pjt.pensieve.wc.model.vo.Todo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService
{
    private final MemoryMapper memorymapper;

    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int saveMemory(Memory memory)
    {
        int result = 0;
        
        if(memory.getMemoryId()!=0)
        {
            result = memorymapper.updateMemory(memory);
        }
        else
        {
            result = memorymapper.insertMemory(memory);
        }
        
        return result;
    }

    @Override
    public Memory getMemory(int memoryId)
    {
        return memorymapper.selectMemory(memoryId);
    }

    
    @Override
    public List<Memory> getMemories(PageInfo pageInfo,String searchWord)
    {
        int limit = pageInfo.getListLimit();
        int offset = (pageInfo.getCurrentPage() - 1) * limit;
        
        RowBounds rowBounds = new RowBounds(offset, limit);      
     
        return memorymapper.selectMemories(rowBounds, searchWord);
    }

    @Override
    public int getMemoryCount(String searchWord)
    {
        return memorymapper.selectMemoryCount(searchWord);
    }

    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int saveTodo(MemoryAjax requestMemory, Todo toDo)
    {
        int result = 0;
        
        result = memorymapper.insertTodo(toDo);
        
        DateTimeFormatter formatter     = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH24:mm:ss");
        result = memorymapper.deleteTodo(toDo.getMemoryId());
        
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
        
        return result;
    }

    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int deleteTodo(int memoryId)
    {
        return memorymapper.deleteTodo(memoryId);
    }

    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int deleteMemory(int memoryId)
    {
        return memorymapper.deleteMemory(memoryId);
    }

    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int checkTodo(int memoryId, LocalDateTime succDate)
    {
        return memorymapper.updateTodo(memoryId, succDate);
    }

}
