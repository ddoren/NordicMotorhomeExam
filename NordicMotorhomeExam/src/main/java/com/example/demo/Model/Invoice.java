package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
// Model class for the invoices
@Entity
public class Invoice {
    //attributes + attributes for queries
    @Id
    private int invoices_id;
    private String res_id;
    private String res_customer;
    private String res_motorhome;
    private String service;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String model_name;
    private String reg_number;
    private int price_per_day;
    private String date_reservation_start;
    private String date_reservation_end;
    private int nr_days;
    private int price_for_extras;
    private int addit_expenses;
    private String  addit_exp_descript;
    private String canceled;
    private int total_price;
    //constructor
    public Invoice() {
    }
    //constructor
    public Invoice(int invoices_id, String res_id, String res_customer, String res_motorhome, String service, String first_name, String last_name, String email, String phone_number, String model_name, String reg_number, int price_per_day, String date_reservation_start, String date_reservation_end, int nr_days, int price_for_extras, int addit_expenses, String addit_exp_descript, String canceled, int total_price) {
        this.invoices_id = invoices_id;
        this.res_id = res_id;
        this.res_customer = res_customer;
        this.res_motorhome = res_motorhome;
        this.service = service;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.model_name = model_name;
        this.reg_number = reg_number;
        this.price_per_day = price_per_day;
        this.date_reservation_start = date_reservation_start;
        this.date_reservation_end = date_reservation_end;
        this.nr_days = nr_days;
        this.price_for_extras = price_for_extras;
        this.addit_expenses = addit_expenses;
        this.addit_exp_descript = addit_exp_descript;
        this.canceled = canceled;
        this.total_price = total_price;
    }

    public int getInvoices_id() {
        return invoices_id;
    }

    public void setInvoices_id(int invoices_id) {
        this.invoices_id = invoices_id;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getRes_customer() {
        return res_customer;
    }

    public void setRes_customer(String res_customer) {
        this.res_customer = res_customer;
    }

    public String getRes_motorhome() {
        return res_motorhome;
    }

    public void setRes_motorhome(String res_motorhome) {
        this.res_motorhome = res_motorhome;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }

    public String getDate_reservation_start() {
        return date_reservation_start;
    }

    public void setDate_reservation_start(String date_reservation_start) {
        this.date_reservation_start = date_reservation_start;
    }

    public String getDate_reservation_end() {
        return date_reservation_end;
    }

    public void setDate_reservation_end(String date_reservation_end) {
        this.date_reservation_end = date_reservation_end;
    }

    public int getNr_days() {
        return nr_days;
    }

    public void setNr_days(int nr_days) {
        this.nr_days = nr_days;
    }

    public int getPrice_for_extras() {
        return price_for_extras;
    }

    public void setPrice_for_extras(int price_for_extras) {
        this.price_for_extras = price_for_extras;
    }

    public int getAddit_expenses() {
        return addit_expenses;
    }

    public void setAddit_expenses(int addit_expenses) {
        this.addit_expenses = addit_expenses;
    }

    public String getAddit_exp_descript() {
        return addit_exp_descript;
    }

    public void setAddit_exp_descript(String addit_exp_descript) {
        this.addit_exp_descript = addit_exp_descript;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
