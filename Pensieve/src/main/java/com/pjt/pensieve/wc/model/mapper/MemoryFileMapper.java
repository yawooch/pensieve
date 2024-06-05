package com.pjt.pensieve.wc.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.pensieve.wc.model.vo.MemoryFile;

@Mapper
public interface MemoryFileMapper
{
    int insertMemoryFile(MemoryFile memoryFile);
}
