package com.zkdx.util;


import java.io.Serializable;

public class CustomizedPrincipal implements Serializable {
    private String loginCategory;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginCategory() {
        return loginCategory;
    }

    public void setLoginCategory(String loginCategory) {
        this.loginCategory = loginCategory;
    }
}
