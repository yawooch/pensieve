package com.pjt.pensieve.main.model.service;

import java.util.List;
import java.util.Map;

import com.pjt.pensieve.main.model.vo.MenuVo;

public interface AdminService
{

    MenuVo getMenuById(String menuId);

    int saveMenu(MenuVo menu);

    List<Map<String, Object>> getMenuForNodes();

    int deleteMenu(MenuVo menu);

}
