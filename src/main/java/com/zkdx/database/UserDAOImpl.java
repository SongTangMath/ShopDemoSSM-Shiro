package com.zkdx.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<User> rowMapper = null;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDAOImpl() {

        rowMapper = new BeanPropertyRowMapper<User>(User.class);
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT* from user where user.id=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String name) {
        String sql = "SELECT* from user where user.username=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return user;
    }

    @Override
    public int modifyUserByUserName(String username, String password, String phone, String address) {
        String sql = "update user set password=?,phone=?,address=? where username=?";
        return jdbcTemplate.update(sql, new Object[] {password, phone, address, username});
    }

    @Override
    public int clearUsers() {
        String sql = "delete from user ";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int insertNewUser(String username, String password, String phone, String address) {
        String sql = "insert into user (username,password,phone,address)values(?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {username, password, phone, address});
    }

    @Override
    public int deleteUserByUserName(String name) {
        String sql = "delete from user where username=?";
        return jdbcTemplate.update(sql, name);
    }

    public static void main(String[] args) {

    }

}
