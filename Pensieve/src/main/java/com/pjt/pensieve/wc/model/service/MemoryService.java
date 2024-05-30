package com.pjt.pensieve.wc.model.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pjt.pensieve.main.common.PageInfo;
import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.Todo;

public interface MemoryService
{
    int saveMemory(Memory memory);

    Memory getMemory(int i);

    List<Memory> getMemories(PageInfo pageInfo);

    int getMemoryCount();

    int saveTodo(Todo toDo);

    int deleteTodo(int memoryId);

    int deleteMemory(int memoryId);

    int checkTodo(int memoryId, LocalDateTime succDate);
}
