package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Extras {
    @Id
    private  int extra_id;
    private String name_extra;
    private int price;
    private String available;
    private String extra_reservation;

    public Extras(){}

    public Extras(int extra_id, String name_extra, int price, String available, String extra_reservation) {
        this.extra_id = extra_id;
        this.name_extra = name_extra;
        this.price = price;
        this.available = available;
        this.extra_reservation = extra_reservation;
    }

    public int getExtra_id() {
        return extra_id;
    }

    public void setExtra_id(int extra_id) {
        this.extra_id = extra_id;
    }

    public String getName_extra() {
        return name_extra;
    }

    public void setName_extra(String name_extra) {
        this.name_extra = name_extra;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getExtra_reservation() {
        return extra_reservation;
    }

    public void setExtra_reservation(String extra_reservation) {
        this.extra_reservation = extra_reservation;
    }
}
