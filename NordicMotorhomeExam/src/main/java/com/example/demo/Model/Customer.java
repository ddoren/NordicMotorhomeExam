package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends Person {

    @Id
    private int cus_id;
    private String address;
    private String cus_zips;
    private String driver_license;
    private String phone_number;

    public Customer() {
    }

    public Customer(int cus_id, String first_name, String last_name, String address, String cus_zips, String driver_license, String email, String phone_number) {
        super(first_name, last_name, email);
        this.cus_id = cus_id;
        this.address = address;
        this.cus_zips = cus_zips;
        this.driver_license = driver_license;
        this.phone_number = phone_number;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCus_zips() {
        return cus_zips;
    }

    public void setCus_zips(String cus_zips) {
        this.cus_zips = cus_zips;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }



    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
