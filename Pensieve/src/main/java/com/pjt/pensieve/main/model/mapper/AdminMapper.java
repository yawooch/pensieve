package com.pjt.pensieve.main.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt.pensieve.main.model.vo.MenuVo;

@Mapper
public interface AdminMapper
{
    MenuVo selectOneMenuById(@Param("menuId") String menuId);

    int insertMenu(MenuVo menu);
}
