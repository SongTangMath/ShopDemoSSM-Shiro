package com.zkdx.database;

import java.io.Serializable;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String identityCard;
    private String name, password, departmentName, job;
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(int id, String identityCard, String name, String password, String departmentName, String job,
        int salary) {
        super();
        this.id = id;
        this.identityCard = identityCard;
        this.name = name;
        this.password = password;
        this.departmentName = departmentName;
        this.job = job;
        this.salary = salary;
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", identityCard=" + identityCard + ", name=" + name + ", password=" + password
            + ", departmentName=" + departmentName + ", job=" + job + ", salary=" + salary + "]";
    }

}
