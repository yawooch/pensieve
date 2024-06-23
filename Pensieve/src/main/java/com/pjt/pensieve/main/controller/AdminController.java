
package com.pjt.pensieve.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        log.info("MenuVo : {}", menu);
        log.info("MenuVo.getMenuId() : {}", menu.getMenuId());
        
        ObjectMapper objectMapper = new ObjectMapper();
//        @SuppressWarnings("unchecked")
//        Map<String, Object> map = objectMapper.convertValue(menu, Map.class);
        
        MenuVo getMenu = adminService.getMenuById(menu.getMenuId());
        
        if(getMenu != null)
        {
            resultMap.put("result", result);
            resultMap.put("msg", "이미 메뉴 아이디가 존재합니다.");
            return ResponseEntity.ok(resultMap);
        }

        result = adminService.saveMenu(menu);
        resultMap.put("result", result);
        
        return ResponseEntity.ok(resultMap);
    }
}