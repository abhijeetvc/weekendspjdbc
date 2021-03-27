package com.springjdbc.weekendspjdbc.controller;

import com.springjdbc.weekendspjdbc.dao.EmployeeDao;
import com.springjdbc.weekendspjdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/getallemployees")
    public List<Employee> getList(){
        return employeeDao.getAllEmployees();
    }

    @PostMapping("/save")
    public String addEmp(@RequestBody Employee employee){
        return employeeDao.addEmployee(employee);
    }

    @GetMapping("/getemp/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        return employeeDao.getEmployeeById(id);
    }

    @GetMapping("/getemployee")
    public List<Employee> getEmployeeBtNameAndCity(@RequestParam String city,
                                             @RequestParam String name){
        return employeeDao.getEmpByNameAndCity(city,name);
    }
}
