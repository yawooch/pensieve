package com.pjt.pensieve.main.model.service;

import com.pjt.pensieve.main.model.vo.MenuVo;

public interface AdminService
{

    MenuVo getMenuById(String menuId);

    int saveMenu(MenuVo menu);

}
