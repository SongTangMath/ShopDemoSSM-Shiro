package com.zkdx.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zkdx.database.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.zkdx.util.SpringUtil;

@Controller
@SessionAttributes(value = {"products"})
public class EditProduct {

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

    @RequiresRoles("seller")
    @RequestMapping(value = "/SetOnShelf/{id}")
    public String setOnShelf(Map<String, Object> map, @PathVariable("id") Integer id) {
        if (id != null) {
            productService.modifyProductStatusByProductId(id, 0);
        }
        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequiresRoles("seller")
    @RequestMapping(value = "/SetOffShelf/{id}")
    public String setOffShelf(Map<String, Object> map, @PathVariable("id") Integer id) {
        if (id != null) {
            productService.modifyProductStatusByProductId(id, 1);
        }
        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequiresRoles("seller")
    @RequestMapping(value = "/EditPlanAndPic/{id}")
    public String editPlanAndPic(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.getProductInfoById(id));
        return "edit_product";
    }

    @RequestMapping(value = "/AddInventory")
    @RequiresRoles("seller")
    public String addInventory(HttpServletRequest request, Map<String, Object> map) {
        Enumeration<String> enumString = request.getParameterNames();
        while (enumString.hasMoreElements()) {
            String en = enumString.nextElement();
            System.out.println(en + " " + request.getParameter(en));

            if (en.startsWith("buyProductID") && !"".equals(request.getParameter(en))) {

                int value = -1;
                try {

                    value = Integer.parseInt(request.getParameter(en));
                    en = en.substring(12);
                    int productID = Integer.parseInt(en);
                    System.out.println("productID " + productID);
                    if (value <= 0) {
                        continue;
                    }
                    ProductInfo info = productService.getProductInfoById(productID);

                    productService.modifyProductIntentoryQuantityByProductId(productID, value);

                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException");
                }

            }
        }
        map.put("products", productService.listAllProducts());
        return "purchaser";
    }

    @RequestMapping(value = "/ModifyProduct/{id}")
    @RequiresRoles("seller")
    public String modifyPlanAndPic(HttpServletRequest request, Map<String, Object> map, @PathVariable("id") Integer id,
                                   String pictureUrl, String productPlan,
                                   @RequestParam(value = "newpicture", required = false) MultipartFile multipartFile) {

        productService.modifyProductPlanByProductID(id, productPlan);
        productService.modifyProductPictureLinkByProductID(id, pictureUrl);

        System.out.println(multipartFile);

        if (multipartFile != null && multipartFile.getSize() > 0) {
            String filePathPart1 = request.getServletContext().getRealPath("/static");
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
            long time = System.currentTimeMillis();
            String filePathPart2 = time + multipartFile.getOriginalFilename();
            String newPictureLink = basePath + "static/" + filePathPart2;

            File file = new File(filePathPart1);
            if (!file.exists()) {
                file.mkdirs();
            }

            file = new File(file, filePathPart2);
            try {
                multipartFile.transferTo(file);
                productService.modifyProductPictureLinkByProductID(id, newPictureLink);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequestMapping(value = "AddNewProduct")
    @RequiresRoles("seller")
    public String addNewProduct(Map<String, Object> map, String productName, Integer price, Integer buyingPrice,
                                String level2) {
        System.out.println(productName + " " + price + " " + buyingPrice + " " + level2);
        if (level2 != null) {
            level2 = level2.trim();
        }
        if (level2 != null && !"".equals(level2) && price != null && buyingPrice != null) {
            System.out.println("new Product "+productName+" "+ price+" "+buyingPrice+" "+level2);
            productService.insertNewProduct(productName, 0, price, 0, "", "", buyingPrice, level2);
        }
        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequestMapping(value = "AddNewCategory")
    @RequiresRoles("seller")
    public String addNewCategory(Map<String, Object> map, String isTop, String parentName, String name) {
        if (isTop != null && name != null && parentName != null) {
            System.out.println("isTop: " + isTop);
            int categoryLevel = "1".equals(isTop) ? 0 : 1;
            System.out.println("new Category " + name + " " + parentName + " 0 " + " " + categoryLevel);
            categoryService.insertNewCategory(name, parentName, 0, categoryLevel);
        }
        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequestMapping(value = "/AddProductsFromExcel")
    @RequiresRoles("seller")
    public String addProductsFromExcel(Map<String, Object> map, HttpServletRequest request,
                                       @RequestParam(value = "excelFile", required = false) MultipartFile multipartFile) {
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";

        File file = null;

        String fileName = multipartFile.getOriginalFilename();
        System.out.println("fileSize:" + multipartFile.getSize());
        String filePathPart1 = request.getServletContext().getRealPath("/UploadedExcels");
        long time = System.currentTimeMillis();
        String filePathPart2 = time + fileName;

        file = new File(filePathPart1);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file, filePathPart2);
        System.out.println(file.getAbsolutePath());
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Workbook workBook = null;
        String path = file.getAbsolutePath();
        if (path.endsWith("xlsx")) {
            try {

                workBook = new XSSFWorkbook(file);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (path.endsWith("xls")) {
            try {
                workBook = new HSSFWorkbook(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (workBook == null) {
            return "purchaser";
        }
        Sheet sheet = workBook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("firstRowNum: " + firstRowNum + "lastRowNum: " + lastRowNum);
        for (int rIndex = firstRowNum; rIndex <= lastRowNum; rIndex++) {

            Row row = sheet.getRow(rIndex);
            if (row == null) {
                continue;
            }
            int firstCellNum = row.getFirstCellNum();
            int lastCellNum = row.getLastCellNum();

            System.out.println("firstCellNum: " + firstCellNum + "lastCellNum: " + lastCellNum);
            if (rIndex == 0) {
                if (firstCellNum != 0 || lastCellNum != 5) {
                    break;
                }
                boolean checkFirstRow = true;
                for (int cIndex = firstCellNum; cIndex <= lastCellNum; cIndex++) {
                    Cell cell = row.getCell(cIndex);
                    String text = formatter.formatCellValue(cell);
                    System.out.print(cIndex + " " + text + " ");
                    switch (cIndex) {

                        case 0:
                            if (!"序号".equals(text)) {
                                checkFirstRow = false;
                            }
                            break;
                        case 1:
                            if (!"商品名称".equals(text)) {
                                checkFirstRow = false;
                            }
                            break;
                        case 2:
                            if (!"商品售价".equals(text)) {
                                checkFirstRow = false;
                            }
                            break;
                        case 3:
                            if (!"商品进价".equals(text)) {
                                checkFirstRow = false;
                            }
                            break;
                        case 4:
                            if (!"商品分类".equals(text)) {
                                checkFirstRow = false;
                            }
                            break;
                        default:
                            break;
                    }

                }
                if (!checkFirstRow) {
                    System.out.println("输入文件格式错误");
                    break;
                }
            } else {
                int price = -1, buyingPrice = -1;
                String productname = null, productCategory = null;

                for (int cIndex = firstCellNum; cIndex <= lastCellNum; cIndex++) {
                    Cell cell = row.getCell(cIndex);
                    String text = formatter.formatCellValue(cell);
                    System.out.print(cIndex + " " + text + " ");

                    switch (cIndex) {

                        case 1:
                            productname = text;
                            break;
                        case 2:
                            try {
                                price = Integer.parseInt(text);
                            } catch (NumberFormatException e) {
                                System.out.println("NumberFormatException");
                            }

                            break;
                        case 3:
                            try {
                                buyingPrice = Integer.parseInt(text);
                            } catch (NumberFormatException e) {
                                System.out.println("NumberFormatException");
                            }
                            break;
                        case 4:
                            productCategory = text;
                            break;
                        default:
                            break;
                    }

                }

                if (price == -1 || buyingPrice == -1 || productname == null || "".equals(productname)
                        || productCategory == null || "".equals(productCategory)) {
                    continue;
                }
                productService.insertNewProduct(productname, 0, buyingPrice, 0, "", "", buyingPrice, productCategory);
            }

        }

        List<ProductInfo> list = productService.listAllProducts();
        map.put("products", list);
        return "purchaser";
    }

    @RequestMapping(value = "/DeleteCategory")
    @RequiresRoles("seller")
    public String deleteCategory() {
        return "delete_category";
    }

    @RequestMapping(value = "/GiveUpDeleteCategory")
    @RequiresRoles("seller")
    public String giveUpDeleteCategory() {
        return "purchaser";
    }

    @RequestMapping(value = "/DeleteCategoryConfirmed")
    @RequiresRoles("seller")
    public String deleteCategoryConfirmed(String categoryNameToDel) {
        if (categoryNameToDel == null || "".equals(categoryNameToDel)) {
            return "purchaser";
        } else {
            categoryService.deleteCategoryAndItsSubCategoriesByName(categoryNameToDel);
        }
        return "purchaser";
    }

    @RequestMapping(value = "/EditExtendedAttribute/{id}")
    @RequiresRoles("seller")
    public String editExtendedAttribute(@PathVariable("id") Integer id, Map<String, Object> map) {
        if (id == null) {
            return "purchaser";
        } else {
            map.put("productInfo", productService.getProductInfoById(id));
            map.put("listAttributes", extendedAttributeService.listAttributesByProductID(id));
        }
        return "edit_extended_attribute";
    }

    @RequestMapping(value = "/GiveUp")
    @RequiresRoles("seller")
    public String giveUp() {
        return "purchaser";
    }

    @RequestMapping(value = "/DeleteExtendedAttribute/{id}")
    @RequiresRoles("seller")
    public String deleteExtendedAttribute(@PathVariable("id") Integer id, Map<String, Object> map) {
        if (id == null) {
            return "purchaser";
        } else {
            extendedAttributeService.deleteExtendedAttributeByID(id);
            map.put("productInfo", productService.getProductInfoById(id));
            map.put("listAttributes", extendedAttributeService.listAttributesByProductID(id));
        }
        return "edit_extended_attribute";
    }

    @RequestMapping(value = "/AddExtendedAttribute/{id}")
    @RequiresRoles("seller")
    public String addExtendedAttribute(@PathVariable("id") Integer id, String attributeName, String attributeValue,
                                       Map<String, Object> map) {
        if (id == null || attributeName == null || attributeValue == null) {
            return "purchaser";
        } else {
            extendedAttributeService.insertNewExtendedAttribute(id, attributeName, attributeValue);
            map.put("productInfo", productService.getProductInfoById(id));
            map.put("listAttributes", extendedAttributeService.listAttributesByProductID(id));
        }
        return "edit_extended_attribute";
    }
}
