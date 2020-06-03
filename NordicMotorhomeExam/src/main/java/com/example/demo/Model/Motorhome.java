package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Motorhome {

    @Id
    private int motor_id;
    private String model_name;
    private String reg_number;
    private int motor_model;
    private String status_rent;
    private int price_per_day;
    private int mileage;
    private int capacity;


    public Motorhome(){}

    public Motorhome(int motor_id, String model_name, String reg_number, int motor_model, String status_rent, int price_per_day, int mileage, int capacity) {
        this.motor_id = motor_id;
        this.model_name = model_name;
        this.reg_number = reg_number;
        this.motor_model = motor_model;
        this.status_rent = status_rent;
        this.price_per_day = price_per_day;
        this.mileage = mileage;
        this.capacity = capacity;
    }

    public int getMotor_id() {
        return motor_id;
    }

    public void setMotor_id(int motor_id) {
        this.motor_id = motor_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
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

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
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
