
package com.pjt.pensieve.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.main.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
    
    // 2. Authentication �� �Ű������� ������������ ��� ���
//    public String adminView(Authentication authentication){
    // 3. @AuthenticationPrincipal ����ϴ� ���
    @GetMapping("/admin")
    public ModelAndView adminView(ModelAndView modelAndView, HttpServletRequest request)
    {
        Member loginMember = (Member)request.getSession().getAttribute("loginMember"); 
        
        System.out.println("loginMember.getRole() : " + loginMember.getRole());
        
        modelAndView.setViewName("admin/settings");
        
//         1. SecurityContextHolder ����ϴ� ���
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member loginMember = (Member)authentication.getPrincipal();
        return modelAndView; 
    }
}