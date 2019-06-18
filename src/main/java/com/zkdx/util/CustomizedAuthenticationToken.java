package com.zkdx.util;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;


public class CustomizedAuthenticationToken extends UsernamePasswordToken implements Serializable {
    private String loginCategory;

    public String getLoginCategory() {
        return loginCategory;
    }

    public void setLoginCategory(String loginCategory) {
        this.loginCategory = loginCategory;
    }

    public CustomizedAuthenticationToken(String username, String password) {
        super(username, password);

    }


    @Override
    public CustomizedPrincipal getPrincipal() {
        CustomizedPrincipal principal = new CustomizedPrincipal();
        principal.setLoginCategory(loginCategory);
        principal.setUsername(getUsername());
        return principal;
    }


}
