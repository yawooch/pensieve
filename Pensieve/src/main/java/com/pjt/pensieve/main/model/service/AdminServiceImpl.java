package com.pjt.pensieve.main.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.pensieve.main.model.mapper.AdminMapper;
import com.pjt.pensieve.main.model.vo.MenuVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            result = mapper.updateMenu(menu);
        }
        else
        {
            result = mapper.insertMenu(menu);
        }
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getMenuForNodes()
    {
        List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> menus   = new ArrayList<Map<String,Object>>();
        List<MenuVo> menuVos              = mapper.selectAll();
        int maxLevel                      = mapper.selectMaxLevel();
        ObjectMapper objectMapper         = new ObjectMapper();

        log.info("menuVos  : {}", menuVos);
        log.info("maxLevel : {}", maxLevel);
        
        for (MenuVo menuVo : menuVos)
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> menu = objectMapper.convertValue(menuVo, Map.class);
            
            menu.remove("menuCreateDate");
            menu.put("text", menu.get("menuName").toString());
            menu.put("href", menu.get("menuUrl").toString());
            
            menus.add(menu);
        }
        results = menus.stream().filter(menu -> Integer.parseInt(menu.get("menuLv").toString()) == 1).collect(Collectors.toList());
        makeMenuNodes(results, menus);
        log.info("results : {}", results);
        
        return results;
    }

    private void makeMenuNodes(List<Map<String, Object>> results, List<Map<String, Object>> menus)
    {
        for (Map<String, Object> result : results)
        {
            String menuId = result.get("menuId").toString();
            List<Map<String, Object>> nodes = menus.stream().filter(menu -> menu.get("menuParentId").toString().equals(menuId)).collect(Collectors.toList()); 
            log.info("nodes : {}", nodes);
            
            if(nodes.size() != 0 && nodes != null)
            {
                result.put("nodes", nodes);
                
                makeMenuNodes(nodes, menus);
            }
        }
    }

    @Override
    @Transactional
    public int deleteMenu(MenuVo menu)
    {
        int result = 0;
        
        result = deleteChildrenMenu(menu, result);
        
        return result;
    }

    private int deleteChildrenMenu(MenuVo menu, int result)
    {
        List<MenuVo> menuVos = mapper.selectMenusByParentId(menu.getMenuId());
        
        if(menuVos.size() != 0 || menuVos != null)
        {
            for (MenuVo menuVo : menuVos)
            {
                result += deleteChildrenMenu(menuVo, result);
            }
        }
        result += mapper.deleteMenu(menu);
        
        return result;
    }
    
}
