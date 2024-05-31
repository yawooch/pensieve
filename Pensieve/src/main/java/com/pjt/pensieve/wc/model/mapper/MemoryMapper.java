package com.pjt.pensieve.wc.model.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.pjt.pensieve.wc.model.vo.Memory;
import com.pjt.pensieve.wc.model.vo.Todo;

@Mapper
public interface MemoryMapper
{
    int insertMemory(Memory memory);

    Memory selectMemory(int memoryId);

    List<Memory> selectMemories(RowBounds rowBounds, @Param("searchWord") String searchWord);

    int selectMemoryCount(@Param("searchWord") String searchWord);

    int insertTodo(Todo toDo);

    int deleteTodo(int memoryId);

    int deleteMemory(int memoryId);

    int updateTodo(@Param("memoryId") int memoryId, @Param("succDate") LocalDateTime succDate);

    int updateMemory(Memory memory);
}
