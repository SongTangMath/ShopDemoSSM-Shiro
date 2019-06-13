package com.zkdx.servlet;

import java.util.*;

import com.zkdx.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkdx.util.SpringUtil;

@Controller
public class CustomerService {
    /*
    private EmployeeService employeeService = (EmployeeService)SpringUtil.getBean("employeeService");
    private UserService userService = (UserService)SpringUtil.getBean("userService");
    private ProductService productService = (ProductService)SpringUtil.getBean("productService");
    private OrderInfoService orderInfoService = (OrderInfoService)SpringUtil.getBean("orderInfoService");
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

    @RequestMapping("CustomerServiceQueryUser")
    public String customerServiceQueryUser(Map<String, Object> map, String username) {
        System.out.println(username);
        if (username == null) {
            return "customer_service";
        } else {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                map.put("userByCustomerService", user);
            }
            map.put("userOrderInfoMap", orderInfoService.mapOrdersByUsername(username));
        }
        int totalQuantity = orderInfoService.getTotalOrderQuantity();
        int totalPages = -1;
        if (totalQuantity % 10 == 0) {
            totalPages = totalQuantity / 10;
        } else {
            totalPages = totalQuantity / 10 + 1;
        }
        map.put("totalPages", totalPages);
        return "customer_service";
    }
    @RequestMapping("ListOrderByPage")
    public String listOrderByPage(Integer orderListIndex,Map<String,Object>map) {
        System.out.println("orderListIndex "+orderListIndex);
        int totalQuantity = orderInfoService.getTotalOrderQuantity();
        int totalPages = -1;
        if (totalQuantity % 10 == 0) {
            totalPages = totalQuantity / 10;
        } else {
            totalPages = totalQuantity / 10 + 1;
        }
        map.put("totalPages", totalPages);
        if(orderListIndex==null||orderListIndex<=0) {             
        return "customer_service";}
        else {
            
            map.put("userOrderInfoList", orderInfoService.listOrdersByIndice(orderListIndex+1, 10));
        }
        return "customer_service";
    }
}
