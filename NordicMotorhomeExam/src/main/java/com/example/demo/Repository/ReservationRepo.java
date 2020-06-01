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
        String sql = "SELECT res_id, res_customer, res_motorhome, invoice_id, first_name, last_name, email, phone_number,  model_name, reg_number, \n" +
                "\t   price_per_day, date_made, date_reservation_start, date_reservation_end, \n" +
                "       DATEDIFF(r.date_reservation_end, r.date_reservation_start) AS nr_days, GROUP_CONCAT(name_extra SEPARATOR ',   ') as extra_products,  SUM(e.price) AS price_for_extras, season, r.price\n" +
                "FROM reservations r\n" +
                "JOIN customers c ON r.res_customer = c.cus_id\n" +
                "JOIN motorhomes m ON r.res_motorhome = m.motor_id\n" +
                "JOIN carmodel cm ON m.motor_model = cm.model_id\n" +
                "LEFT JOIN extras_in_reservations er ON er.reservation_id = r.res_id\n" +
                "LEFT JOIN extras e ON er.extra_id = e.extra_id\n" +
                "WHERE invoice_id IS NULL " +
                "GROUP BY res_id;\n";
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
        String sql = "SELECT res_id, res_customer, res_motorhome, invoice_id, first_name, last_name, email, phone_number,  model_name, reg_number, \n" +
                "\t   price_per_day, date_made, date_reservation_start, date_reservation_end, \n" +
                "     DATEDIFF(r.date_reservation_end, r.date_reservation_start) AS nr_days, GROUP_CONCAT(name_extra SEPARATOR ',   ') as extra_products,  SUM(e.price) AS price_for_extras, season, r.price\n" +
                "FROM reservations r\n" +
                "JOIN customers c ON r.res_customer = c.cus_id\n" +
                "JOIN motorhomes m ON r.res_motorhome = m.motor_id\n" +
                "JOIN carmodel cm ON m.motor_model = cm.model_id\n" +
                "LEFT JOIN extras_in_reservations er ON er.reservation_id = r.res_id\n" +
                "LEFT JOIN extras e ON er.extra_id = e.extra_id\n" +
                "WHERE res_id = ? AND invoice_id IS NULL " +
                "GROUP BY res_id;\n";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.queryForObject(sql, rowMapper, id);
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

    public int getLastInsertedId(){
        String sql = "SELECT last_insert_id() AS last_id;";
        Integer last_id = template.queryForObject(sql, Integer.class);
        return last_id;
    }

    public int countReservationDays(String start_date, String end_date){
        String sql = "SELECT DATEDIFF(?, ?) AS Result";
        Integer Result = template.queryForObject(sql, Integer.class, end_date, start_date);
        return Result;
    }

    public void stopDisplayReservation(int reservation_id, int invoice_id){
        String sql = "UPDATE reservations SET invoice_id = ? WHERE res_id = ?;";
        template.update(sql, invoice_id, reservation_id);
    }

    public String getCurrentDate(){
        String sql = "SELECT CURDATE() AS cur_date;";
        String cur_date = template.queryForObject(sql, String.class);
        return cur_date;
    }

}
