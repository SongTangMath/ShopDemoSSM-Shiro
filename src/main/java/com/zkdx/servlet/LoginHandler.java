package com.zkdx.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.*;
import com.zkdx.util.*;

@Controller

@SessionAttributes(value = {"user", "admin", "employees", "products", "orderInfoMap", "employee"})
public class LoginHandler {

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

    @RequestMapping("/login")
    public String login(HttpServletRequest request,
                        Map<String, Object> map) {

        CustomizedPrincipal customizedPrincipal = (CustomizedPrincipal) SecurityUtils.getSubject().getPrincipal();
        if (customizedPrincipal == null) {
            return "login_failed";
        }
        String username = customizedPrincipal.getUsername();
        String loginCategory = customizedPrincipal.getLoginCategory();
        if (request.getSession().getAttribute("shiroLoginFailure") != null) {
            return "login_failed";
        }


        System.out.println("in LoginHandler: " + loginCategory + " " + username);
        if ("admin".equals(loginCategory)) {
            map.put("adminName", "admin");
            map.put("employee", new Employee());
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        } else if ("user".equals(loginCategory)) {
            User user = userService.getUserByUsername(username);
            map.put("user", user);
            map.put("products", productService.listStatus0Products());
            map.put("orderInfoMap", orderInfoService.mapOrdersByUsername(username));
            return "products";
        } else if ("employee".equals(loginCategory)) {
            Employee employee = employeeService.getEmployeeByIdentityCard(username);
            if (employee != null) {
                System.out.println(employee);
                map.put("employee", employee);
                if ("销售部".equals(employee.getDepartmentName())) {
                    List<ProductInfo> list = productService.listAllProducts();
                    map.put("products", list);
                    return "purchaser";
                } else if ("客服部".equals(employee.getDepartmentName())) {
                    int totalQuantity = orderInfoService.getTotalOrderQuantity();
                    int totalPages = -1;
                    if (totalQuantity % 10 == 0) {
                        totalPages = totalQuantity / 10;
                    } else {
                        totalPages = totalQuantity / 10 + 1;
                    }
                    map.put("totalPages", totalPages);
                    return "customer_service";
                } else {
                    return "employee";
                }
            }

        }
        return "login_failed";
    }
}
