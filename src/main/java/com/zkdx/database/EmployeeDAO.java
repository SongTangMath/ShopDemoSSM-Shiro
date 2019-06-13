package com.zkdx.database;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface EmployeeDAO {
    public Employee getEmployeeById(int id);

    public Employee getEmployeeByIdentityCard(String identityCard);

    public List<Employee> listAllEmployees();

    public int clearEmployees();

    public int insertNewEmployee(@Param("identityCard") String identityCard,@Param("name") String name,
      @Param("password")String password,@Param("departmentName") String departmentName,
                                 @Param("job")String job,  @Param("salary")  int salary);

    public int modifyEmployeeById(@Param("id")int id, @Param("identityCard")String identityCard,
        @Param("name")String name, @Param("password")String password, @Param("departmentName")String departmentName,
                                  @Param("job") String job, @Param("salary")int salary);

    public int deleteEmployeeById(int id);

}
