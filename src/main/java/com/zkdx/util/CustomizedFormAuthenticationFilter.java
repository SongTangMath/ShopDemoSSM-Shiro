package com.zkdx.util;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomizedFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        if(!(token instanceof CustomizedAuthenticationToken)) {
            return super.onLoginSuccess(token, subject, request, response);
        }
        else{
            HttpServletRequest request1=(HttpServletRequest)request;
            request1.getSession().setAttribute("loginCategory",((CustomizedAuthenticationToken) token).getLoginCategory());
            return super.onLoginSuccess(token, subject, request, response);
        }
    }

    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
        CustomizedAuthenticationToken token=new CustomizedAuthenticationToken(username,password);
        token.setLoginCategory(request.getParameter("loginCategory"));
        token.setRememberMe(isRememberMe(request));
        token.setHost(getHost(request));
        return token;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        return super.createToken(request, response);
    }

}
