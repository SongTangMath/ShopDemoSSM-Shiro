package com.zkdx.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.zkdx.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkdx.util.SpringUtil;

@Controller
public class UserRegister {
    /*
    private UserService userService = (UserService)SpringUtil.getBean("userService");

     */

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ExtendedAttributeService extendedAttributeService;
    @ResponseBody
    @RequestMapping(value = "/testUsername/{username}")
    public boolean usernameUsedAlready(@PathVariable("username") String username) {

        try {
            username = new String(username.getBytes("ISO8859-1"), "UTF-8");
            System.out.println(username);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(username);
        return userService.getUserByUsername(username) != null;
    }

    @RequestMapping(value = "/UserRegister")
    public String register(String username, String password, String phone, String address) {
        userService.insertNewUser(username, password, phone, address);
        return "redirect:/index.jsp";
    }
}
