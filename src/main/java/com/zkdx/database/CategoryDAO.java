package com.zkdx.database;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface CategoryDAO {
    public Category getCategoryById(int id);

    public Category getCategoryByName(String name);

    public int deleteCategoryByName(String name);

    public int insertNewCategory(String name, int parentId,int isEnd, int categoryStatus, int categoryLevel);
/**
 * 设定某个类别是否为叶子(这个类别没有子类别)
 * @param name 需要设定状态的类别名称
 * @param isEnd 是否为叶子.1表示是叶子0表示不是
 * @return
 */
    public int setIsEnd(String name, int isEnd);

    /**
     * 设定某个类别的状态.目前设计为status=0表示状态正常 可以根据后面的需要定义其他的状态码的含义
     * 
     * @param name
     *            需要设定状态的类别名称
     * @param status
     *            希望设定的状态码
     * @return 设定是否成功
     */
    public int setStatus(String name, int status);

    /**
     * 这个方法返回所有以parentId为上级类别代码的类别
     * 
     * @param parentName
     *            上级类别名称
     * @return
     */
    public List<Category> listCategoriesByParentId(int parentId);

    /**
     * 这个方法返回所有顶级类别构成的List
     * 
     * @return
     * 
     */
    public List<Category> listLevel0Categories();
}
