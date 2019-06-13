package com.zkdx.database;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    @Override
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public Employee getEmployeeById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return employeeDAO.getEmployeeById(id);
        }
    }

    @Override
    public Employee getEmployeeByIdentityCard(String identityCard) {
        if (!validateArg(identityCard)) {
            return null;
        } else {
            return employeeDAO.getEmployeeByIdentityCard(identityCard);
        }
    }

    @Override
    public List<Employee> listAllEmployees() {
        // TODO Auto-generated method stub
        return employeeDAO.listAllEmployees();
    }

    @Override
    public int clearEmployees() {
        // TODO Auto-generated method stub
        return employeeDAO.clearEmployees();
    }

    @Override
    public int insertNewEmployee(String identityCard, String name, String password, String departmentName, String job,
        int salary) {
        if (!validateArg(identityCard) || !validateArg(name) || !validateArg(password) || !validateArg(departmentName)
            || !validateArg(job) || salary <= 0) {
            return 0;
        } else if (employeeDAO.getEmployeeByIdentityCard(identityCard) == null) {
            return employeeDAO.insertNewEmployee(identityCard, name, password, departmentName, job, salary);
        } else {
            return 0;
        }
    }

    @Override
    public int modifyEmployeeById(int id, String identityCard, String name, String password, String departmentName,
        String job, int salary) {
        if (!validateArg(identityCard) || !validateArg(name) || !validateArg(password) || !validateArg(departmentName)
            || !validateArg(job) || salary <= 0) {
            return 0;
        }
        Employee employee = employeeDAO.getEmployeeById(id);
        if (identityCard.equals(employee.getIdentityCard())) {
            return employeeDAO.modifyEmployeeById(id, identityCard, name, password, departmentName, job, salary);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteEmployeeById(int id) {
        // TODO Auto-generated method stub
        if (id <= 0) {
            return 0;
        } else {
            return employeeDAO.deleteEmployeeById(id);
        }
    }

    public static void main(String[] args) {

    }

}
