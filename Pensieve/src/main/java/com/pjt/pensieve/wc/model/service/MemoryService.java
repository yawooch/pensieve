package com.pjt.pensieve.wc.model.service;

import com.pjt.pensieve.wc.model.vo.Memory;

public interface MemoryService
{
    int save(Memory memory);

    Memory getMemoryOne(int i);
}
