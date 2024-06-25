package com.pjt.pensieve.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.main.model.service.AdminService;
import com.pjt.pensieve.main.model.vo.Member;
import com.pjt.pensieve.main.model.vo.MenuVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    
    //2. Authentication 을 매개변수로 직접가져오는 방법 방법
    //public String adminView(Authentication authentication){
    //3. @AuthenticationPrincipal 사용하는 방법
    @GetMapping("/admin")
    public ModelAndView adminView(ModelAndView modelAndView, HttpServletRequest request)
    {
        Member loginMember = (Member)request.getSession().getAttribute("loginMember"); 
        
        System.out.println("loginMember.getRole() : " + loginMember.getRole());
        
        modelAndView.setViewName("admin/settings");
        
        //1. SecurityContextHolder 사용하는 방법
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Member loginMember = (Member)authentication.getPrincipal();
        return modelAndView; 
    }
    @PostMapping("/admin/saveMenu")
    public ResponseEntity<Map<String, Object>> saveMenu(@RequestBody MenuVo menu)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = 0;

        MenuVo getMenu = adminService.getMenuById(menu.getMenuId());
        
        if(getMenu == null)
        {
            resultMap.put("msg", "메뉴가 추가 되었습니다.");
        }
        else 
        {
            resultMap.put("msg", "메뉴가 수정 되었습니다.");
            menu.setMenuCreateDate(getMenu.getMenuCreateDate());
        }
        
        result = adminService.saveMenu(menu);
        
        resultMap.put("result", result); 
        
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/admin/getMenu")
    public ResponseEntity<Map<String, Object>> getMenu()
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<Map<String, Object>> nodes = adminService.getMenuForNodes();  
        
        if(nodes == null || nodes.size() == 0)
        {
            resultMap.put("result", 0);
            resultMap.put("msg", "메뉴가 존재하지 않습니다.");
            return ResponseEntity.ok(resultMap);
        }
        
        resultMap.put("result", 1); 
        resultMap.put("nodes" , nodes); 
        
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/admin/deleteMenu")
    public ResponseEntity<Map<String, Object>> deleteMenu(@RequestBody MenuVo menu)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = 0;

        log.info("MenuVo : {}", menu);
        log.info("MenuVo.getMenuId() : {}", menu.getMenuId());
        
        MenuVo getMenu = adminService.getMenuById(menu.getMenuId());
        
        if(getMenu.getMenuId() == null)
        {
            resultMap.put("result", result);
            resultMap.put("msg", "메뉴 아이디가 존재하지 않습니다.");
            return ResponseEntity.ok(resultMap);
        }
        result = adminService.deleteMenu(menu);
        
        resultMap.put("result", result); 
        resultMap.put("msg", result + "개의 메뉴가 삭제되었습니다.");
        
        return ResponseEntity.ok(resultMap);
    }
}