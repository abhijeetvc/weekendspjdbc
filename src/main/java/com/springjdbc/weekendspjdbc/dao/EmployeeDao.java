package com.springjdbc.weekendspjdbc.dao;

import com.springjdbc.weekendspjdbc.model.Employee;
import com.springjdbc.weekendspjdbc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        String sql="select * from employee";
        List<Employee> list=jdbcTemplate
                .query(sql,new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    @Override
    public String addEmployee(Employee employee) {
        String sql="insert into employee values(?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{employee.getId(),employee.getName(),
                employee.getCity(),employee.getDepartment().getId()});
        return "Employee added";
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        String sql="select * from employee where id=?";
        Employee emp=jdbcTemplate
                .queryForObject(sql,new Object[]{id},new int[]{Types.INTEGER},
                        new BeanPropertyRowMapper<>(Employee.class));
        return emp;
    }

    @Override
    public List<Employee> getEmpByNameAndCity(String city, String name) {
        String sql="select * from employee where city=? and name=?";
        List<Employee> list=jdbcTemplate
                .query(sql,new Object[]{city,name},new int[]{Types.VARCHAR,Types.VARCHAR},new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    @Override
    public List<Map<String, Object>> getCombinedData() {
        String sql="select a.id,a.name,a.city,b.name as deptName from employee a,department b where a.dept_id=b.id";

        return jdbcTemplate.queryForList(sql);
    }
}
