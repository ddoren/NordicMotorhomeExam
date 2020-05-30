package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {
    @Autowired
    JdbcTemplate template;

    public Employee findEmployee(String email,String employ_pass) {
        String sql = "SELECT * FROM employees WHERE email = ? AND employ_pass = ?";

        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
         return template.queryForObject(sql,rowMapper,email,employ_pass);
    }
    public List<Employee> findAllEmployee()
    {
        String sql="SELECT * FROM employees;";
        RowMapper<Employee>rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql,rowMapper);
    }
}