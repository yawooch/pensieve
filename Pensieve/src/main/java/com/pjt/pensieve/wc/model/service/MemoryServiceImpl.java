package com.pjt.pensieve.wc.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.mapper.MemoryMapper;
import com.pjt.pensieve.wc.model.vo.Memory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService
{
    private final MemoryMapper memorymapper;
    
    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int save(Memory memory)
    {
        int result = 0;
        
        log.info("memory : ", memory);
        
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

}
