package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private String res_id;
    private String res_customer;
    private String res_motorhome;
    private String date_made;
    private String date_reservation;
    private String invoice_id;
    private String price;

    public Reservation() {
    }

    public Reservation(String res_id, String res_customer, String res_motorhome, String date_made, String date_reservation, String invoice_id, String price) {
        this.res_id = res_id;
        this.res_customer = res_customer;
        this.res_motorhome = res_motorhome;
        this.date_made = date_made;
        this.date_reservation = date_reservation;
        this.invoice_id = invoice_id;
        this.price = price;
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

    public String getDate_made() {
        return date_made;
    }

    public void setDate_made(String date_made) {
        this.date_made = date_made;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
