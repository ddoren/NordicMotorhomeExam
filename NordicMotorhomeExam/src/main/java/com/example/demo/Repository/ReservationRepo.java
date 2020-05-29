package com.example.demo.Repository;

import com.example.demo.Model.Reservation;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepo {

    @Autowired
    JdbcTemplate template;

    public List<Reservation> fetchAll(){
        String sql = "SELECT * FROM reservations;";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper);
    }

    public void addReservation(Reservation r){
        String sql = "INSERT INTO reservations VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, ?);";
        template.update(sql, r.getRes_customer(), r.getRes_motorhome(), r.getDate_made(),
                        r.getDate_reservation_start(), r.getDate_reservation_end(), r.getSeason(),
                        r.getPrice());
    }

    public Reservation findReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE res_id = ?;";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        Reservation r = template.queryForObject(sql, rowMapper, id);
        return r;
    }

    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE res_id = ?;";
        template.update(sql, id);
    }

    public void updateReservation(int id, Reservation r) {
        String sql = "UPDATE reservations SET res_motorhome = ?, " +
                     "date_reservation_start = ?, date_reservation_end = ?, season = ?, " +
                     "price = ? WHERE res_id = ?;";
        template.update(sql, r.getRes_motorhome(), r.getDate_reservation_start(),
                        r.getDate_reservation_end(), r.getSeason(), r.getPrice(), id);
    }

    public List<Motorhome> availableMotorhome(String date_reservation_start, String  date_reservation_end){
        String sql = "SELECT motor_id, reg_number, status_rent, mileage, capacity\n" +
                "FROM motorhomes m\n" +
                "LEFT JOIN reservations r\n" +
                "ON m.motor_id = r.res_motorhome\n" +
                "WHERE status_rent = \"Ready\" OR \n" +
                "((? < date_reservation_start AND ? < date_reservation_start) OR \n" +
                "(? > date_reservation_end AND ? > date_reservation_end));";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper, date_reservation_start, date_reservation_end,
                              date_reservation_start, date_reservation_end);
    }
}
