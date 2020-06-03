package com.example.demo;

import com.example.demo.Model.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {

    //Testing The First Option, less than
    @Test
    void calculateCancelPriceTest1() {
        Reservation test = new Reservation();
        assertEquals(500, test.calculateCancelPrice(1000, 20));
    }

    @Test
    void calculateCancelPriceTest2() {
        Reservation test = new Reservation();
        assertEquals(1600, test.calculateCancelPrice(2000, 10));
    }

    @Test
    void calculateCancelPriceTest3() {
        Reservation test = new Reservation();
        assertEquals(3000, test.calculateCancelPrice(3000, 55));
    }
}
