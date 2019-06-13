package com.zkdx.database;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public OrderInfoDAO getOrderInfoDAO() {
        return orderInfoDAO;
    }

    @Override
    public void setOrderInfoDAO(OrderInfoDAO orderInfoDAO) {
        this.orderInfoDAO = orderInfoDAO;
    }

    @Override
    public List<OrderInfo> listOrdersByUsername(String name) {
        if (!validateArg(name)) {
            return new ArrayList<OrderInfo>();
        } else {
            return orderInfoDAO.listOrdersByUsername(name);
        }
    }

    @Override
    public List<OrderInfo> listOrdersByTime(Timestamp beginDate, Timestamp endDate) {
        if (beginDate == null || endDate == null) {
            return new ArrayList<OrderInfo>();
        } else {
            return orderInfoDAO.listOrdersByTime(beginDate, endDate);
        }

    }

    @Override
    public List<OrderInfo> listOrdersByIndice(int beginPageIndex, int recordPerPage) {
        if (beginPageIndex < 0 || recordPerPage <= 0) {
            return new ArrayList<OrderInfo>();
        }
        return orderInfoDAO.listOrdersByIndice(beginPageIndex, recordPerPage);
    }

    @Override
    public TreeMap<Timestamp, ArrayList<OrderInfo>> mapOrdersByUsername(String name) {
        TreeMap<Timestamp, ArrayList<OrderInfo>> map =
            new TreeMap<Timestamp, ArrayList<OrderInfo>>((a, b) -> b.compareTo(a));

        if (!validateArg(name)) {
            return map;
        } else {
            List<OrderInfo> list = orderInfoDAO.listOrdersByUsername(name);
            for (OrderInfo info : list) {
                if (!map.containsKey(info.getOrderDatetime())) {
                    ArrayList<OrderInfo> tempList = new ArrayList<OrderInfo>();
                    tempList.add(info);
                    map.put(info.getOrderDatetime(), tempList);

                } else {
                    map.get(info.getOrderDatetime()).add(info);
                }
            }
        }
        return map;
    }

    @Override
    public int insertNewOrderInfo(String username, Timestamp orderDatetime, String productName, int productQuantity,
        int price, String extendedAttributeString, int buyingPrice, String productCategory) {
        if (!validateArg(username) || orderDatetime == null || !validateArg(productName) || productQuantity < 0
            || price <= 0 || extendedAttributeString == null || buyingPrice < 0) {
            return 0;
        } else {
            return orderInfoDAO.insertNewOrderInfo(username, orderDatetime, productName, productQuantity, price,
                extendedAttributeString, buyingPrice, productCategory);
        }
    }

    @Override
    public int getTotalOrderQuantity() {
        return orderInfoDAO.getTotalOrderQuantity();
    }

}
