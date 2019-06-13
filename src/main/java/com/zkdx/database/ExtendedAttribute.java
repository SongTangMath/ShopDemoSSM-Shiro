package com.zkdx.database;

import java.io.Serializable;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class ExtendedAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    private int attributeID;
    private int productID;
    private String attributeName, attributeValue;

    public int getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(int attributeID) {
        this.attributeID = attributeID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public ExtendedAttribute() {
        super();
    }

    public ExtendedAttribute(int attributeID, int productID, String attributeName, String attributeValue) {
        super();
        this.attributeID = attributeID;
        this.productID = productID;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return "ExtendedAttribute [attributeID=" + attributeID + ", productID=" + productID + ", attributeName="
            + attributeName + ", attributeValue=" + attributeValue + "]";
    }

}
