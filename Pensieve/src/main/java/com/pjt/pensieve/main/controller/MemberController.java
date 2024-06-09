package com.pjt.pensieve.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pjt.pensieve.main.model.service.MemberService;
import com.pjt.pensieve.main.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MemberController {
    private final MemberService memberService;
    
    @PostMapping("/member/idCheck")
    public ResponseEntity<Map<String,Object>> idCheck(@RequestBody Map<String, Object> requestMap)
    {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        
        System.out.println("requestMap : " + requestMap);
        
        String memberId = requestMap.get("memberId").toString();
        
        System.out.println("memberId : " + memberId);
        
        Member resultMember = memberService.checkMemberId(memberId);
        
        System.out.println("resultMap : " + resultMember);
        
        resultMap.put("result", resultMember);
        
        return ResponseEntity.ok(resultMap); 
    }
}