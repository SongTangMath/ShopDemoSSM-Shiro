package com.zkdx.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class Quit {

    @RequestMapping("/Quit")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("adminName");
        request.getSession().removeAttribute("employee");
        request.getSession().removeAttribute("employees");
        request.getSession().removeAttribute("user");
        return "redirect:/index.jsp";
    }
}
