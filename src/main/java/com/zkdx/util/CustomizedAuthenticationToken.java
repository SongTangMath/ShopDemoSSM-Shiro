package com.zkdx.util;

import org.apache.shiro.authc.UsernamePasswordToken;


public class CustomizedAuthenticationToken extends UsernamePasswordToken {
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
}