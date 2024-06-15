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
        log.info("preHandle() ȣ��");

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");

        //�α��� ������ �ִ��� Ȯ��
        if (loginMember == null || request.getSession() == null)
        {
            //Ajax ��û���� Ȯ���Ѵ�.ajax success�Լ����� ó��������Ѵ�.
            if(request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
            {
                response.setHeader("REQUIRE_LOGIN", "true");

                return false;
            }
            
            request.setAttribute("msg", "�α��� �� �̿��� �����մϴ�.");
            request.setAttribute("location", "/login");
            request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
