package com.zkdx.util;

import com.zkdx.database.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginRealm extends AuthorizingRealm {


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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (!(token instanceof CustomizedAuthenticationToken)) {
            System.out.println("not CustomizedAuthenticationToken");

            return null;
        } else {
            CustomizedAuthenticationToken token1 = (CustomizedAuthenticationToken) token;
            String loginCategory = token1.getLoginCategory();
            String username = token1.getPrincipal().getUsername();

            String password = new String(token1.getPassword());
            if ("admin".equals(loginCategory)) {
                System.out.println("admin verifying ");
                if ("admin".equals(username) && "123".equals(password)) {
                    return new SimpleAuthenticationInfo(token1.getPrincipal(), token1.getCredentials(), getName());
                } else {
                    return null;
                }
            } else if ("employee".equals(loginCategory)) {
                System.out.println("employee verifying ");
                Employee employee = employeeService.getEmployeeByIdentityCard(username);
                if (employee != null && employee.getPassword().equals(password)) {
                    return new SimpleAuthenticationInfo(token1.getPrincipal(), token1.getCredentials(), getName());
                } else {
                    return null;
                }

            } else if ("user".equals(loginCategory)) {
                System.out.println("user verifying ");
                User user = userService.getUserByUsername(username);
                if (user != null && password.equals(user.getPassword())) {
                    return new SimpleAuthenticationInfo(token1.getPrincipal(), token1.getCredentials(), getName());
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        CustomizedPrincipal customizedPrincipal = (CustomizedPrincipal) principals.getPrimaryPrincipal();
        System.out.println("customizedPrincipal: " + customizedPrincipal);

        String loginCategory = customizedPrincipal.getLoginCategory();
        System.out.println(loginCategory);
        String username = customizedPrincipal.getUsername();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if ("admin".equals(loginCategory)) {
            info.addRole("admin");
        }
        if ("employee".equals(loginCategory)) {
            Employee emp = employeeService.getEmployeeByIdentityCard(username);
            if (emp != null && "客服部".equals(emp.getDepartmentName())) {
                info.addRole("customerService");
            } else if (emp != null && "销售部".equals(emp.getDepartmentName())) {
                info.addRole("seller");
            }
        }
        if ("user".equals(loginCategory)) {
            info.addRole("user");
        }


        return info;
    }
}
