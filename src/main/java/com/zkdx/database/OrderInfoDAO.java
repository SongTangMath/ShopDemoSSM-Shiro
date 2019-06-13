package com.zkdx.database;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface OrderInfoDAO {
    public List<OrderInfo> listOrdersByUsername(String name);

    public List<OrderInfo> listOrdersByTime(java.sql.Timestamp beginDate, java.sql.Timestamp endDate);

    public List<OrderInfo> listOrdersByIndice(int beginPageIndex, int recordPerPage);

    public int insertNewOrderInfo(String username, java.sql.Timestamp orderDatetime, String productName,
        int productQuantity, int price, String extendedAttributeString, int buyingPrice, String productCategory);

    public int getTotalOrderQuantity();

}
