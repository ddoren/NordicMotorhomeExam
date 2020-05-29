package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    public void addCustomer(Customer c) {
        String sql = "INSERT INTO customers VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getAddress(), c.getCus_zips(),
                        c.getDriver_license(), c.getEmail(), c.getPhone_number());
    }

    public Customer findCustomerByLicense(String driver_license) {
        String sql ="SELECT * FROM customers WHERE driver_license = ?;";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = template.queryForObject(sql, rowMapper, driver_license);
        return customer;
    }

    public int countReservationDays(String start_date, String end_date){
        String sql = "SELECT DATEDIFF(?, ?) AS Result";
        Integer Result = template.queryForObject(sql, Integer.class, end_date, start_date);
        return Result;
    }
}
