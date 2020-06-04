package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Exceptions;
import com.example.demo.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
/*this class is used for
* Contacting the repo class and also catching
* the SQL errors and returning a custom message to the Whitelabel Error Page*/
@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Employee findEmployee(String email, String employ_pass)
    {    try {
        return loginRepository.findEmployee(email,employ_pass);
    } catch(DuplicateKeyException e) {
        throw new Exceptions("User already exist");
    } catch(DataAccessException e) {
        throw new Exceptions("Database doesn't have the selected user or is unreachable!");
    }

    }
    public List<Employee> findAllEmployee()
    {
        return loginRepository.findAllEmployee();
    }

}
