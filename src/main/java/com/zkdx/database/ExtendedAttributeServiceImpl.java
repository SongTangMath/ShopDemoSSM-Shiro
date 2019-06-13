package com.zkdx.database;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkdx.database.*;

public class ExtendedAttributeServiceImpl implements ExtendedAttributeService {
    @Autowired
    private ExtendedAttributeDAO extendedAttributeDAO;

    @Override
    public ExtendedAttributeDAO getExtendedAttributeDAO() {
        return extendedAttributeDAO;
    }

    @Override
    public void setExtendedAttributeDAO(ExtendedAttributeDAO extendedAttributeDAO) {
        this.extendedAttributeDAO = extendedAttributeDAO;
    }

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public List<ExtendedAttribute> listAttributesByProductName(String name) {
        if (!validateArg(name)) {
            return new ArrayList<ExtendedAttribute>();
        } else {
            return extendedAttributeDAO.listAttributesByProductName(name);
        }
    }

    @Override
    public List<ExtendedAttribute> listAttributesByProductID(int id) {
        if (id <= 0) {
            return new ArrayList<ExtendedAttribute>();
        } else {
            return extendedAttributeDAO.listAttributesByProductID(id);
        }
    }

    @Override
    public int insertNewExtendedAttribute(int productID, String attributeName, String attributeValue) {
        if (productID <= 0 || !validateArg(attributeName) || attributeValue == null) {
            return 0;
        } else {
            return extendedAttributeDAO.insertNewExtendedAttribute(productID, attributeName, attributeValue);
        }
    }

    @Override
    public int deleteExtendedAttributeByID(int id) {
        if (id <= 0) {
            return 0;
        } else {
            return extendedAttributeDAO.deleteExtendedAttributeByID(id);
        }
    }

    @Override
    public HashMap<String, ArrayList<String>> getExtendedAttributeMapByProductID(int id) {
        HashMap<String, ArrayList<String>> attributeValueMap = new HashMap<String, ArrayList<String>>();

        List<ExtendedAttribute> list = listAttributesByProductID(id);
        for (ExtendedAttribute attr : list) {
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(attr.getAttributeValue().split(" ")));
            attributeValueMap.put(attr.getAttributeName(), temp);
        }
        return attributeValueMap;
    }

}
