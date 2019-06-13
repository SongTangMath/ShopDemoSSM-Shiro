package com.zkdx.database;

import java.util.List;

public interface CategoryService {

    public CategoryDAO getCategoryDAO();

    public void setCategoryDAO(CategoryDAO categoryDAO);

    public Category getCategoryById(int id);

    public Category getCategoryByName(String name);

    public int deleteCategoryAndItsSubCategoriesByName(String name);

    public int insertNewCategory(String name, String parentName, int categoryStatus, int categoryLevel);

    public int setIsEnd(String name, int isEnd);

    public int setStatus(String name, int status);

    public List<Category> listCategoriesByParentName(String parentName);

    public List<Category> listLevel0Categories();
}
