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
    @Transactional // ������ ����� �ڵ� �ѹ� 
    public int saveMemory(Memory memory)
    {
        int result = 0;
        
        result = memorymapper.insertMemory(memory);
        
        return result;
    }

    @Override
    public Memory getMemory(int memoryId)
    {
        return memorymapper.selectMemory(memoryId);
    }

    
    @Override
    public List<Memory> getMemories(PageInfo pageInfo)
    {
        int limit = pageInfo.getListLimit();
        int offset = (pageInfo.getCurrentPage() - 1) * limit;
        
        RowBounds rowBounds = new RowBounds(offset, limit);      
     
        return memorymapper.selectMemories(rowBounds);
    }

    @Override
    public int getMemoryCount()
    {
        return memorymapper.selectMemoryCount();
    }

    @Override
    @Transactional // ������ ����� �ڵ� �ѹ� 
    public int saveTodo(Todo toDo)
    {
        int result = 0;
        
        result = memorymapper.insertTodo(toDo);
        
        return result;
    }

    @Override
    @Transactional // ������ ����� �ڵ� �ѹ� 
    public int deleteTodo(int memoryId)
    {
        return memorymapper.deleteTodo(memoryId);
    }

    @Override
    @Transactional // ������ ����� �ڵ� �ѹ� 
    public int deleteMemory(int memoryId)
    {
        return memorymapper.deleteMemory(memoryId);
    }

    @Override
    @Transactional // ������ ����� �ڵ� �ѹ� 
    public int checkTodo(int memoryId, LocalDateTime succDate)
    {
        return memorymapper.updateTodo(memoryId, succDate);
    }

}
