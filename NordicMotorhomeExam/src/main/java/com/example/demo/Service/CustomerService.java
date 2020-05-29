package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public void addCustomer(Customer c) {
        customerRepo.addCustomer(c);
    }

    public Customer findCustomerByLicense(String driver_license) {
        return customerRepo.findCustomerByLicense(driver_license);
    }

    public int countReservationDays(String start_date, String end_date){
        return customerRepo.countReservationDays(start_date, end_date);
    }
}
