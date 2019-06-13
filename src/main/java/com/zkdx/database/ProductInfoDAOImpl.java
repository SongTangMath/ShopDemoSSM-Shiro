package com.zkdx.database;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.transaction.annotation.Transactional;

public class ProductInfoDAOImpl implements ProductInfoDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<ProductInfo> rowMapper = null;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductInfoDAOImpl() {

        rowMapper = new BeanPropertyRowMapper<ProductInfo>(ProductInfo.class);
    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        String sql = "SELECT* from product_info where product_info.id=?";

        ProductInfo info = null;

        try {
            info = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return info;
    }

    @Override
    public ProductInfo getProductInfoByProductName(String name) {
        String sql = "SELECT* from product_info where product_info.product_name=?";

        ProductInfo info = null;
        try {
            info = jdbcTemplate.queryForObject(sql, rowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return info;
    }

    @Override
    public int modifyProductByProductID(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        String sql = "update product_info set product_name=?,product_status=?,price=?,inventory_quantity=?,"
            + "picture_link=?,product_plan=?,buying_price=?,product_category=? where id=?)";
        return jdbcTemplate.update(sql, new Object[] {productName, productStatus, price, inventoryQuantity, pictureLink,
            productPlan, buyingPrice, productCategory, id});
    }

    @Override
    public int modifyProductPictureLinkByProductID(int id, String pictureLink) {
        String sql = "update product_info set picture_link=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {pictureLink, id});
    }

    @Override
    public int modifyProductPlanByProductName(String productName, String productPlan) {
        String sql = "update product_info set product_plan=? where product_name=?";
        return jdbcTemplate.update(sql, new Object[] {productPlan, productName});
    }

    @Override

    public int setProductIntentoryQuantityByProductId(int id, int number) {

        String sql = "update product_info set inventory_quantity=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {number, id});
    }

    @Override
    public int clearProducts() {
        String sql = "delete from product_info ";
        return jdbcTemplate.update(sql);

    }

    @Override
    public int insertNewProduct(String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        String sql = "insert into product_info (product_name,product_status,price,inventory_quantity,"
            + "picture_link,product_plan,buying_price,product_category) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {productName, productStatus, price, inventoryQuantity, pictureLink,
            productPlan, buyingPrice, productCategory});
    }

    @Override
    public int deleteProductByProductID(int id) {
        String sql = "delete from product_info where id=?";
        return jdbcTemplate.update(sql, new Object[] {id});

    }

    @Override
    public List<ProductInfo> listAllProducts() {
        String sql = "select* from product_info ";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<ProductInfo> listProductsByProductCategory(String pattern) {
        String sql = "select* from product_info where product_category like ? or product_name like ?";
        return jdbcTemplate.query(sql, rowMapper, new Object[] {"%" + pattern + "%", "%" + pattern + "%"});
    }

    @Override
    public int modifyProductStatusByProductId(int id, int status) {
        String sql = "update product_info set product_status=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {status, id});
    }

    @Override
    public List<ProductInfo> listStatus0Products() {
        String sql = "select* from product_info where product_status=0";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<ProductInfo> listStatus0ProductsByProductCategory(String pattern) {
        String sql =
            "select* from product_info where (product_category like ? or product_name like ?) and product_status=0";
        return jdbcTemplate.query(sql, rowMapper, new Object[] {"%" + pattern + "%", "%" + pattern + "%"});
    }

    @Override
    public int modifyProductPlanByProductID(int id, String productPlan) {
        String sql = "update product_info set product_plan=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {productPlan, id});
    }

}
