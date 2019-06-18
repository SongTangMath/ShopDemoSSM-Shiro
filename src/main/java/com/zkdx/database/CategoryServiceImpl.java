package com.zkdx.database;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public Category getCategoryById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return categoryDAO.getCategoryById(id);
        }
    }

    @Override
    public Category getCategoryByName(String name) {
        if (!validateArg(name)) {
            return null;
        } else {
            return categoryDAO.getCategoryByName(name);
        }
    }

    @Override
    public int deleteCategoryAndItsSubCategoriesByName(String name) {
        if (!validateArg(name)) {
            return 0;
        } else {
            List<Category> list = listCategoriesByParentName(name);
            for (Category category : list) {
                deleteCategoryAndItsSubCategoriesByName(category.getCategoryName());
            }
             categoryDAO.deleteCategoryByName(name);
        }
        return 1;
    }

    @Override
    public int insertNewCategory(String name, String parentName, int categoryStatus, int categoryLevel) {
        if (!validateArg(name) || parentName == null) {
            System.out.println("invalid args");
            return 0;
        } else if (categoryDAO.getCategoryByName(name) == null) {
            Category father = null;
            if (categoryLevel != 0) {
                father = categoryDAO.getCategoryByName(parentName);
                if (father != null) {
                    categoryDAO.setIsEnd(father.getCategoryName(), 0);
                    return categoryDAO.insertNewCategory(name, father.getCategoryID(), 1, categoryStatus, father.getCategoryLevel()+1);
                }
                else{
                    System.out.println("parent_category doesn't exist");
                    return 0;
                }

            } else {
                return categoryDAO.insertNewCategory(name, -1,1, categoryStatus, categoryLevel);
            }
        }
        else{
            System.out.println("category already exists");
            return 0;
        }

    }

    @Override
    public int setIsEnd(String name, int isEnd) {
        if (!validateArg(name)) {
            return 0;
        } else if (isEnd != 0 && isEnd != 1) {
            return 0;
        } else {
            return categoryDAO.setIsEnd(name, isEnd);
        }
    }

    @Override
    public int setStatus(String name, int status) {
        if (!validateArg(name)) {
            return 0;
        } else {
            return categoryDAO.setStatus(name, status);
        }
    }

    @Override
    public List<Category> listCategoriesByParentName(String parentName) {
        List<Category>list=new ArrayList<Category>();
        if (!validateArg(parentName)) {
            return list;
        }


        Category parent = getCategoryByName(parentName);
        if (parent == null) {
            return list;
        }else {
            return categoryDAO.listCategoriesByParentId(parent.getCategoryID());
        }
    }

    @Override
    public List<Category> listLevel0Categories() {
        // TODO Auto-generated method stub
        return categoryDAO.listLevel0Categories();
    }

}
