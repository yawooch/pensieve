package com.pjt.pensieve.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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
    @GetMapping("/idCheck")
    public ResponseEntity<Map<String,Object>> idCheck(@RequestParam String memberId)
    {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        
        resultMap.put("result", memberService.isDuplicated(memberId));
        
        return ResponseEntity.ok(resultMap); 
    }
    
    /** 회원을 등록한다 */
    @PostMapping("/joinMember")
    public ResponseEntity<Map<String,Object>> joinMember(@RequestBody Member requestMember)
    {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        
        System.out.println("requestMember : " + requestMember);
        
        int result = memberService.saveMember(requestMember);
        
        
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

        
        Member loginMember = memberService.checkMemberId(memberId, memberPw);
        
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
    

    @GetMapping("/logout")
    public String logout(SessionStatus status) 
    {
        // 세션 영역으로 확장된 Attribute를 지워준다.
        status.setComplete();
        
        return "redirect:/";
    }

    /** 마이페이지로 간다. */
    @GetMapping("/mypage")
    public ModelAndView mypage(ModelAndView modelAndView, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        
        Member loginMember = (Member)session.getAttribute("loginMember");
        String memberId = loginMember.getMemberId(); 
        String memberPw = loginMember.getMemberPw(); 
        System.out.println("memberId : " + memberId);
        System.out.println("memberPw : " + memberPw);

        modelAndView.setViewName("main/settings");
        
        return modelAndView; 
    }
}