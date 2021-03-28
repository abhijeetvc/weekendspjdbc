package com.springjdbc.weekendspjdbc.repository;

import com.springjdbc.weekendspjdbc.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {

    List<Employee> getAllEmployees();

    String addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    List<Employee> getEmpByNameAndCity(String city,String name);

    List<Map<String,Object>> getCombinedData();
}
