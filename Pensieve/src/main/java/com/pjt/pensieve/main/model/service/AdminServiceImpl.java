package com.pjt.pensieve.main.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.pensieve.main.model.mapper.AdminMapper;
import com.pjt.pensieve.main.model.vo.MenuVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService
{
    private final AdminMapper mapper;
    
    @Override
    public MenuVo getMenuById(String menuId)
    {
        return mapper.selectOneMenuById(menuId);
    }

    @Override
    @Transactional
    public int saveMenu(MenuVo menu)
    {
        int result = 0;
        
        if(menu.getMenuCreateDate() != null)
        {
            //update
        }
        else
        {
            result = mapper.insertMenu(menu);
        }
        
        return result;
    }
    
}
