package com.pjt.pensieve.wc.model.service;

import java.util.List;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.vo.Memory;

public interface MemoryService
{
    int save(Memory memory);

    Memory getMemory(int i);

    List<Memory> getMemories(PageInfo pageInfo);

    int getMemoryCount();
}
