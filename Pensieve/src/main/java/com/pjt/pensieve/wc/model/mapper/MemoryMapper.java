package com.pjt.pensieve.wc.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.pjt.pensieve.wc.model.vo.Memory;

@Mapper
public interface MemoryMapper
{
    int insertMemory(Memory memory);

    Memory selectMemory(int memoryId);

    List<Memory> selectMemories(RowBounds rowBounds);

    int selectMemoryCount();
}
