package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Motorhome {

    @Id
    private int motor_id;
    private String reg_number;
    private int motor_model;
    private String status_rent;
    private int mileage;
    private int capacity;

    public int getMotor_id() {
        return motor_id;
    }

    public void setMotor_id(int motor_id) {
        this.motor_id = motor_id;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public int getMotor_model() {
        return motor_model;
    }

    public void setMotor_model(int motor_model) {
        this.motor_model = motor_model;
    }

    public String getStatus_rent() {
        return status_rent;
    }

    public void setStatus_rent(String status_rent) {
        this.status_rent = status_rent;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
