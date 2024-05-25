package com.pjt.pensieve.wc.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.wc.model.mapper.MemoryMapper;
import com.pjt.pensieve.wc.model.vo.Memory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService
{
    private MemoryMapper memorymapper;
    
    @Override
    @Transactional // 에러가 생기면 자동 롤백 
    public int save(Memory memory)
    {
        int result = 0;
        
        log.info("memory : ", memory);
        
        result = memorymapper.insertMemory(memory);
        
        return result;
    }

}
