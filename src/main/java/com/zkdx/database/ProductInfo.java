package com.zkdx.database;

import java.io.Serializable;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String productName;
    private int productStatus;
    private int price;
    private int inventoryQuantity;
    private String pictureLink;
    private String productPlan;
    private int buyingPrice;
    private String productCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(String productPlan) {
        this.productPlan = productPlan;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public ProductInfo() {

    }

    public ProductInfo(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        super();
        this.id = id;
        this.productName = productName;
        this.productStatus = productStatus;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.pictureLink = pictureLink;
        this.productPlan = productPlan;
        this.buyingPrice = buyingPrice;
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "ProductInfo [id=" + id + ", productName=" + productName + ", productStatus=" + productStatus
            + ", price=" + price + ", inventoryQuantity=" + inventoryQuantity + ", pictureLink=" + pictureLink
            + ", productPlan=" + productPlan + ", buyingPrice=" + buyingPrice + ", productCategory=" + productCategory
            + "]";
    }

}
