package com.zkdx.database;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return userDAO.getUserById(id);
        }
    }

    @Override
    public User getUserByUsername(String name) {
        if (!validateArg(name)) {
            return null;
        } else {
            return userDAO.getUserByUsername(name);
        }
    }

    @Override
    public int modifyUserByUserName(String username, String password, String phone, String address) {
        if (!validateArg(username) || !validateArg(password)) {
            return 0;
        } else {
            return userDAO.modifyUserByUserName(username, password, phone, address);
        }
    }

    @Override
    public int clearUsers() {

        return userDAO.clearUsers();
    }

    @Override
    public int insertNewUser(String username, String password, String phone, String address) {
        if (!validateArg(username) || !validateArg(password)) {
            return 0;
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return userDAO.insertNewUser(username, password, phone, address);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteUserByUserName(String name) {
        if (!validateArg(name)) {
            return 0;
        } else {
            return userDAO.deleteUserByUserName(name);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
