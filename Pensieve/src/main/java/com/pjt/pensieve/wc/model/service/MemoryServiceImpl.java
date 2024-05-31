package com.pjt.pensieve.wc.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.mapper.MemoryMapper;
import com.pjt.pensieve.wc.model.vo.Memory;
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
    public int saveTodo(Todo toDo)
    {
        int result = 0;
        
        result = memorymapper.insertTodo(toDo);
        
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
