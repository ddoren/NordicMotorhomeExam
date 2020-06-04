package com.example.demo.Repository;


import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MotorhomeRepo {
    @Autowired
    JdbcTemplate template;

    //this class is used for querying things in the database
    //the methods use a string variable for the SQL
    //RowMapper is used to Map the returned SQL result to the appropriate model
    //finds all of
    public List<Motorhome> fetchAll() {
        String sql = "SELECT motor_id, model_name, reg_number, motor_model, status_rent, price_per_day, mileage, capacity FROM motorhomes m\t\t\t\n" +
                "JOIN carmodel c\n" +
                "ON m.motor_model = c.model_id";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }
    //add a new motorhome to the DB
    public Motorhome addMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhomes VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        template.update(sql, m.getReg_number(), m.getMotor_model(), m.getStatus_rent(), m.getMileage(), m.getCapacity());
        return null;
    }
    //finds a specific motorhome from the DB
    public Motorhome findMotorhomeById(int id) {
        String sql ="SELECT * FROM motorhomes WHERE motor_id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome motorhome = template.queryForObject(sql, rowMapper, id);
        return motorhome;
    }
    //deletes specific motorhome from the DB
    public void deleteMotorhome(int id) {
        String sql = "DELETE FROM motorhomes WHERE motor_id = ?";
        template.update(sql, id);
    }
    //updates a specific motorhome's data
    public void updateMotorhome(int id, Motorhome m) {
        String sql = "UPDATE motorhomes SET reg_number = ?, motor_model = ?, status_rent = ?, mileage = ?, capacity = ?, price_per_day = ? WHERE motor_id = ?";
        template.update(sql, m.getReg_number(), m.getMotor_model(), m.getStatus_rent(), m.getMileage(), m.getCapacity(), m.getPrice_per_day(), id);
    }
}
