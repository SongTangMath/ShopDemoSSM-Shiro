package com.zkdx.database;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoDAO {
    public ProductInfo getProductInfoById(int id);

    public ProductInfo getProductInfoByProductName(String name);

    public int modifyProductByProductID(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory);

    public int modifyProductPictureLinkByProductID(int id, String pictureLink);

    public int modifyProductPlanByProductName(String productName, String productPlan);

    public int modifyProductPlanByProductID(int id, String productPlan);

    public int setProductIntentoryQuantityByProductId(int id, int number);

    public int modifyProductStatusByProductId(int id, int status);

    public int clearProducts();

    public int insertNewProduct(String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory);

    public int deleteProductByProductID(int id);

    public List<ProductInfo> listAllProducts();

    public List<ProductInfo> listProductsByProductCategory(@Param("pattern") String pattern);

    public List<ProductInfo> listStatus0Products();

    public List<ProductInfo> listStatus0ProductsByProductCategory(@Param("pattern")String pattern);

}
