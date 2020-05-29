package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import com.example.demo.Repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    public List<Reservation> fetchAll(){
        return reservationRepo.fetchAll();
    }

    public void addReservation(Reservation r){
        reservationRepo.addReservation(r);
    }

    public Reservation findReservationById(int id) {
        return reservationRepo.findReservationById(id);
    }

    public void deleteReservation(int id) {
        reservationRepo.deleteReservation(id);
    }

    public void updateReservation(int id, Reservation r) {
        reservationRepo.updateReservation(id, r);
    }
    public List<Motorhome> availableMotorhomes(String date_reservation_start, String  date_reservation_end){
        return reservationRepo.availableMotorhome(date_reservation_start, date_reservation_end);
    }
}
