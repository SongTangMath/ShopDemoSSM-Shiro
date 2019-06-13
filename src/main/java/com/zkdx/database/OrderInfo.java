package com.zkdx.database;

import java.io.Serializable;
import java.sql.*;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int orderID;
    private String username;
    private java.sql.Timestamp orderDatetime;
    private String productName;
    private int productQuantity;
    private int price;
    private String extendedAttributeString;
    private int buyingPrice;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public java.sql.Timestamp getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(java.sql.Timestamp orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExtendedAttributeString() {
        return extendedAttributeString;
    }

    public void setExtendedAttributeString(String extendedAttributeString) {
        this.extendedAttributeString = extendedAttributeString;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public OrderInfo(int orderID, String username, Timestamp orderDatetime, String productName, int productQuantity,
        int price, String extendedAttributeString, int buyingPrice) {
        super();
        this.orderID = orderID;
        this.username = username;
        this.orderDatetime = orderDatetime;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.price = price;
        this.extendedAttributeString = extendedAttributeString;
        this.buyingPrice = buyingPrice;
    }

    public OrderInfo() {
        super();
    }

    @Override
    public String toString() {
        return "OrderInfo [orderID=" + orderID + ", username=" + username + ", orderDatetime=" + orderDatetime
            + ", productName=" + productName + ", productQuantity=" + productQuantity + ", price=" + price
            + ", extendedAttributeString=" + extendedAttributeString + ", buyingPrice=" + buyingPrice + "]";
    }

}
