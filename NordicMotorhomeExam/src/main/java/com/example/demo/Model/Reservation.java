package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private int res_id;
    private String res_customer;
    private String res_motorhome;
    private String date_made;
    private String date_reservation_start;
    private String date_reservation_end;
    private String season;
    private String invoice_id;
    private int price;

    public Reservation() {
    }

    public Reservation(int res_id, String res_customer, String res_motorhome, String date_made, String date_reservation_start, String date_reservation_end, String season, String invoice_id, int price) {
        this.res_id = res_id;
        this.res_customer = res_customer;
        this.res_motorhome = res_motorhome;
        this.date_made = date_made;
        this.date_reservation_start = date_reservation_start;
        this.date_reservation_end = date_reservation_end;
        this.season = season;
        this.invoice_id = invoice_id;
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
