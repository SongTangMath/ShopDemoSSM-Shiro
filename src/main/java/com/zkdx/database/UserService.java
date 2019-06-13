package com.zkdx.database;
/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface UserService {
    public UserDAO getUserDAO();

    public void setUserDAO(UserDAO userDAO);

    public User getUserById(int id);

    public User getUserByUsername(String name);

    public int modifyUserByUserName(String username, String password, String phone, String address);

    public int clearUsers();

    public int insertNewUser(String username, String password, String phone, String address);

    public int deleteUserByUserName(String name);
}
