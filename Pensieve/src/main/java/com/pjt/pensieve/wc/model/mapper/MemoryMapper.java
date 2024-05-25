package com.pjt.pensieve.wc.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.pensieve.wc.model.vo.Memory;

@Mapper
public interface MemoryMapper
{
    int insertMemory(Memory memory);
}
