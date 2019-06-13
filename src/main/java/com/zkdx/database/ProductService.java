package com.zkdx.database;

import java.util.List;

public interface ProductService {

    public ProductInfoDAO getProductInfoDAO();

    public void setProductInfoDAO(ProductInfoDAO productInfoDAO);

    public ProductInfo getProductInfoById(int id);

    public ProductInfo getProductInfoByProductName(String name);

    public int modifyProductByProductID(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory);

    public int modifyProductPictureLinkByProductID(int id, String pictureLink);

    public int modifyProductPlanByProductName(String productName, String productPlan);

    public int modifyProductIntentoryQuantityByProductId(int id, int number);

    public int clearProducts();

    public int insertNewProduct(String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory);

    public int deleteProductByProductID(int id);

    public List<ProductInfo> listAllProducts();

    public List<ProductInfo> listProductsByProductCategory(String pattern);

    public int modifyProductStatusByProductId(int id, int status);

    public List<ProductInfo> listStatus0Products();

    public List<ProductInfo> listStatus0ProductsByProductCategory(String pattern);

    public int modifyProductPlanByProductID(int id, String productPlan);

}
