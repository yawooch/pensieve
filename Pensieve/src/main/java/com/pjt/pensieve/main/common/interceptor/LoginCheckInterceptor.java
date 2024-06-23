package com.pjt.pensieve.main.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.pjt.pensieve.main.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        log.info("preHandle() 호출");

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");

        //로그인 세션이 있는지 확인
        if (loginMember == null || request.getSession() == null)
        {
            //Ajax 요청인지 확인한다.ajax success함수에도 처리해줘야한다.
            if(request.getHeader("X-Requested-With") != null)
            {
                if(request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
                {
                    response.setHeader("REQUIRE_LOGIN", "true");
                    
                    return false;
                }
            }
            
            request.setAttribute("msg", "로그인 후 이용이 가능합니다.");
            request.setAttribute("location", "/login");
            request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
