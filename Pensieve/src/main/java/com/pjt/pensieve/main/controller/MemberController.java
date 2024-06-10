package com.pjt.pensieve.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.pensieve.main.model.service.MemberService;
import com.pjt.pensieve.main.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginMember")
public class MemberController {
    private final MemberService memberService;
    
    /** memberId 중복검사를하는 request */
    @PostMapping("/idCheck")
    public ResponseEntity<Map<String,Object>> idCheck(@RequestBody Map<String, Object> requestMap)
    {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        
        String memberId     = requestMap.get("memberId").toString();
        
        Member resultMember = memberService.checkMemberId(memberId);

        
        resultMap.put("result", resultMember);
        return ResponseEntity.ok(resultMap); 
    }
    
    /** 회원을 등록한다 */
    @PostMapping("/joinMember")
    public ResponseEntity<Map<String,Object>> joinMember(@RequestBody Member requestMember)
    {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        
        System.out.println("requestMember : " + requestMember);
        
        int result = memberService.enrollMember(requestMember);
        
        
        System.out.println("requestMember : " + requestMember);
        
        resultMap.put("result", result);
        
        return ResponseEntity.ok(resultMap); 
    }

    /** 로그인 처리한다. */
    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView,
                          @RequestParam("login-username") String memberId,
                          @RequestParam("login-password") String memberPw)
    {
        System.out.println("memberId : " + memberId);
        System.out.println("memberPw : " + memberPw);

        
        Member loginMember = memberService.checkMemberId(memberId);
        
        if(loginMember != null)
        {
            modelAndView.addObject("loginMember", loginMember);
            modelAndView.setViewName("main/home");
        }
        else
        {
            modelAndView.setViewName("common/login");
        }
        return modelAndView; 
    }
}