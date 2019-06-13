package com.zkdx.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface ExtendedAttributeService {
    public ExtendedAttributeDAO getExtendedAttributeDAO();

    public void setExtendedAttributeDAO(ExtendedAttributeDAO extendedAttributeDAO);

    public List<ExtendedAttribute> listAttributesByProductName(String name);

    public List<ExtendedAttribute> listAttributesByProductID(int id);

    public int insertNewExtendedAttribute(int productID, String attributeName, String attributeValue);

    public int deleteExtendedAttributeByID(int id);

    public HashMap<String, ArrayList<String>> getExtendedAttributeMapByProductID(int id);
}
