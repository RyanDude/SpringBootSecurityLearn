package com.example.demo.Util;

import com.example.demo.Handlers.SecurityAuthenticationFailureHandler;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VertificationCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler=new SecurityAuthenticationFailureHandler();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(!"/auth/form".equals(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            try{
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }catch (VertificationCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse,
                        e);
            }
        }
    }
    public void verificationCode(HttpServletRequest request)throws VertificationCodeException{
        String requestCode=request.getParameter("captcha");
        HttpSession session=request.getSession();
        String savedCode=(String)session.getAttribute("captcha");
        System.err.println(savedCode);
        if(!StringUtils.isEmpty(requestCode)){
            session.removeAttribute("captcha");
        }
        if(StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(savedCode) || !requestCode.equals(savedCode)){
            throw new VertificationCodeException();
        }
    }
}
