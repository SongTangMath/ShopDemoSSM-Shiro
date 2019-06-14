package com.zkdx.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.*;
import com.zkdx.util.SpringUtil;

@Controller
@SessionAttributes(value = {"employees"})
public class EditEmployee {
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
    @RequiresRoles("admin")
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String addNewEmployee(HttpServletRequest request, @RequestParam("identityCard") String identityCard,
        @RequestParam("password") String password, Map<String, Object> map,
        @RequestParam(value = "id", required = false) Integer id, @RequestParam("name") String name,
        @RequestParam("departmentName") String departmentName, @RequestParam("job") String job,
        @RequestParam("salary") Integer salary) {

        map.put("employee", new Employee());
        if (identityCard == null || password == null || name == null || departmentName == null || job == null
            || salary == null) {

            return "manager";
        }

        else {
            if (id == null) {
                System.out.println("insertNewEmployee");
                employeeService.insertNewEmployee(identityCard, name, password, departmentName, job, salary);
            } else {
                System.out.println("modifyEmployeeById" + id);
                System.out.println(salary);
                System.out.println(
                    employeeService.modifyEmployeeById(id, identityCard, name, password, departmentName, job, salary));
            }
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        }

    }
    @RequiresRoles("admin")
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(HttpServletRequest request, @PathVariable("id") Integer id, Map<String, Object> map) {

        map.put("employee", new Employee());
        System.out.println("delete");
        if (id == null) {
            System.out.println("argumentsIncorrect");
            return "manager";
        }

        else {
            System.out.println("deleteEmployeeById" + id);
            employeeService.deleteEmployeeById(id);
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        }

    }
    @RequiresRoles("admin")
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String modifyEmployee(HttpServletRequest request, @PathVariable("id") Integer id, Map<String, Object> map) {

        if (id == null) {
            return "manager";
        } ;

        Employee employee = employeeService.getEmployeeById(id);
        map.put("employee", employee);
        return "modify_employee";

    }

    @RequestMapping("QuerySellingStatus")
    @ResponseBody
    @RequiresRoles("admin")
    public String querySellingStatus(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
        String beginDateString, String endDateString) {
        map.put("employee", new Employee());
        HttpSession session = request.getSession(true);
        System.out.println("beginDateString " + beginDateString);
        System.out.println("endDateString " + endDateString);
        if (beginDateString == null || endDateString == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date beginDate = null, endDate = null;
        try {
            beginDate = simpleDateFormat.parse(beginDateString);
            endDate = simpleDateFormat.parse(endDateString);
        } catch (ParseException e) {
            System.out.println("查询日期格式错误");
        }
        if (beginDate == null || endDate == null) {
            return null;
        }
        System.out.println(beginDate);
        System.out.println(endDate);
        List<OrderInfo> list = orderInfoService.listOrdersByTime(new java.sql.Timestamp(beginDate.getTime()),
            new java.sql.Timestamp(endDate.getTime()));
        Map<String, SellingStatus> sellingStatusMap = new HashMap<String, SellingStatus>();
        for (OrderInfo info : list) {
            int totalCost = info.getBuyingPrice() * info.getProductQuantity();
            int totalProfit = (info.getPrice() - info.getBuyingPrice()) * info.getProductQuantity();
            if (!sellingStatusMap.containsKey(info.getProductName())) {

                sellingStatusMap.put(info.getProductName(),
                    new SellingStatus(info.getProductName(), info.getProductQuantity(), totalCost, totalProfit));

            } else {
                SellingStatus status = sellingStatusMap.get(info.getProductName());
                status.addQuantitySold(info.getProductQuantity());
                status.addTotalCost(totalCost);
                status.addTotalProfit(totalProfit);
            }
        }
        List<SellingStatus> sellingStatusList = new ArrayList<SellingStatus>();
        for (String key : sellingStatusMap.keySet()) {
            sellingStatusList.add(sellingStatusMap.get(key));
        }
        Collections.sort(sellingStatusList, (a, b) -> b.getQuantitySold() - a.getQuantitySold());
        // for(SellingStatus status:sellingStatusList) response.getWriter().append(status.toString()+"\n");
        Workbook workBook = new SXSSFWorkbook();
        Sheet sheet = workBook.createSheet("账户表数据");

        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 35 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("销量排名");
        row.createCell(1).setCellValue("商品名称");

        row.createCell(2).setCellValue("销售数量");
        row.createCell(3).setCellValue("该商品总成本");
        row.createCell(4).setCellValue("该商品总利润");
        int totalCost = 0, totalProfit = 0;
        for (int i = 0; i < sellingStatusList.size(); i++) {
            SellingStatus status = sellingStatusList.get(i);
            row = sheet.createRow(i + 1);

            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(status.getProductName());

            row.createCell(2).setCellValue(status.getQuantitySold());
            row.createCell(3).setCellValue(status.getTotalCost());
            row.createCell(4).setCellValue(status.getTotalProfit());
            totalCost += status.getTotalCost();
            totalProfit += status.getTotalProfit();
        }
        row = sheet.createRow(sellingStatusList.size() + 2);
        row.createCell(0).setCellValue("以上商品总成本");
        row.createCell(1).setCellValue("以上商品总利润");

        row = sheet.createRow(sellingStatusList.size() + 3);
        row.createCell(0).setCellValue(totalCost);
        row.createCell(1).setCellValue(totalProfit);

        String fileName = "" + beginDateString + "到" + endDateString + "销售数据.xlsx";
        try {
            response.setHeader("Content-disposition",
                "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0);
        try {
            workBook.write(response.getOutputStream());
            workBook.close();
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
