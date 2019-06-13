package com.zkdx.database;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface ExtendedAttributeDAO {
    public List<ExtendedAttribute> listAttributesByProductName(String name);

    public List<ExtendedAttribute> listAttributesByProductID(int id);

    public int insertNewExtendedAttribute(int productID, String attributeName, String attributeValue);

    public int deleteExtendedAttributeByID(int id);
}
