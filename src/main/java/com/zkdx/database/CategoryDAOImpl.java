package com.zkdx.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CategoryDAOImpl implements CategoryDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<Category> rowMapper = null;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CategoryDAOImpl() {
        super();
        // rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
        rowMapper = new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setCategoryID(rs.getInt("category_id"));
                category.setCategoryLevel(rs.getInt("category_level"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryStatus(rs.getInt("category_status"));
                category.setIsEnd(rs.getInt("is_end"));
                category.setParentID(rs.getInt("parent_id"));
                return category;
            }
        };

    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT* from category where category_id=?";
        Category category = null;
        try {
            category = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {

        }
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        String sql = "SELECT* from category where category_name=?";
        Category category = null;
        try {
            category = jdbcTemplate.queryForObject(sql, rowMapper, name);
        } catch (EmptyResultDataAccessException e) {

        }
        return category;

    }

    @Override
    public int deleteCategoryByName(String name) {
        String sql = "delete from category where category_name=?";
        return jdbcTemplate.update(sql, name);
    }

    @Override
    public int insertNewCategory(String name, String parentName, int categoryStatus, int categoryLevel) {
        int parentID = -1;
        int parentLevel = -1;
        Category test = getCategoryByName(name);
        Category parent = null;
        if (test != null) {
            return 0;
        }
        if (categoryLevel != 0) {
            parent = getCategoryByName(parentName);
            if (parent == null) {
                return 0;
            }

            else {
                parentID = parent.getCategoryID();
                parentLevel = parent.getCategoryLevel();
                setIsEnd(parent.getCategoryName(), 0);
            }
        }
        String sql =
            "insert into category(category_name,parent_id,is_end,category_status,category_level) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {name, parentID, 1, categoryStatus, parentLevel + 1});
    }

    @Override
    public int setIsEnd(String name, int isEnd) {
        String sql = "update category set is_end=? where category_name=?";
        return jdbcTemplate.update(sql, new Object[] {isEnd, name});
    }

    @Override
    public int setStatus(String name, int status) {
        String sql = "update category set category_status=? where category_name=?";
        return jdbcTemplate.update(sql, new Object[] {status, name});
    }

    @Override
    public List<Category> listCategoriesByParentId(int parentId) {

        String sql = "select* from category where parent_id=?";
        return jdbcTemplate.query(sql, rowMapper, parentId);
    }

    @Override
    public List<Category> listLevel0Categories() {
        String sql = "select* from category where parent_id=-1";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
