package com.example.demo.Model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Validation {
    @javax.persistence.Id
    private String email;
    private String employ_pass;

    public Validation() {
    }

    public Validation(String email, String employ_pass) {
        this.email = email;
        this.employ_pass = employ_pass;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmploy_pass() {
        return employ_pass;
    }

    public void setEmploy_pass(String employ_pass) {
        this.employ_pass = employ_pass;
    }
}
