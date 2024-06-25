package com.pjt.pensieve.main.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt.pensieve.main.model.vo.MenuVo;

@Mapper
public interface AdminMapper
{
    MenuVo selectOneMenuById(@Param("menuId") String menuId);

    int insertMenu(MenuVo menu);

    List<MenuVo> selectAll();

    int selectMaxLevel();

    int updateMenu(MenuVo menu);

    int deleteMenu(MenuVo menu);

    List<MenuVo> selectMenusByParentId(String menuId);
}
