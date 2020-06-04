package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private int res_id;
    private String res_customer;
    private String res_motorhome;
    private String invoice_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String model_name;
    private String reg_number;
    private String price_per_day;
    private String date_made;
    private String date_reservation_start;
    private String date_reservation_end;
    private String nr_days;
    private String extra_products;
    private int price_for_extras;
    private String season;
    private int distance;
    private int price_for_transfer;
    private int price;


    public Reservation() {
    }

    public Reservation(int res_id, String res_customer, String res_motorhome, String invoice_id, String first_name, String last_name, String email, String phone_number, String model_name, String reg_number, String price_per_day, String date_made, String date_reservation_start, String date_reservation_end, String nr_days, String extra_products, int price_for_extras, String season, int price) {
        this.res_id = res_id;
        this.res_customer = res_customer;
        this.res_motorhome = res_motorhome;
        this.invoice_id = invoice_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.model_name = model_name;
        this.reg_number = reg_number;
        this.price_per_day = price_per_day;
        this.date_made = date_made;
        this.date_reservation_start = date_reservation_start;
        this.date_reservation_end = date_reservation_end;
        this.nr_days = nr_days;
        this.extra_products = extra_products;
        this.price_for_extras = price_for_extras;
        this.season = season;
        this.price = price;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
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

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
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

    public String getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(String price_per_day) {
        this.price_per_day = price_per_day;
    }

    public String getDate_made() {
        return date_made;
    }

    public void setDate_made(String date_made) {
        this.date_made = date_made;
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

    public String getNr_days() {
        return nr_days;
    }

    public void setNr_days(String nr_days) {
        this.nr_days = nr_days;
    }

    public String getExtra_products() {
        return extra_products;
    }

    public void setExtra_products(String extra_products) {
        this.extra_products = extra_products;
    }

    public int getPrice_for_extras() {
        return price_for_extras;
    }

    public void setPrice_for_extras(int price_for_extras) {
        this.price_for_extras = price_for_extras;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return res_id + " " + res_customer + " " +res_motorhome + " " +invoice_id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice_for_transfer() {
        return price_for_transfer;
    }

    public void setPrice_for_transfer(int price_for_transfer) {
        this.price_for_transfer = price_for_transfer;
    }

    public int calculateCancelPrice(int reservation_price, int number_days) {
        int total_price = this.price;
        if (number_days <= 1) {
            total_price *= 0.95;
        } else if (number_days <= 14 && number_days >= 2) {
            total_price *= 0.80;
        } else if (number_days <= 49 && number_days >= 15) {
            total_price *= 0.50;
        }
        return total_price;
    }

    public double addDropOff(String pick_in_store, int distance){
        int result = this.price;
        if (pick_in_store.equals("No")){
            this.price_for_transfer = (int) (distance * 0.70);
            result +=  this.price_for_transfer;
        }
        return result;
    }
}
