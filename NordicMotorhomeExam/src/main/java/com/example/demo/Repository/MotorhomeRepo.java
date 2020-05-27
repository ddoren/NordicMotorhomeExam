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

    public List<Motorhome> fetchAll() {
        String sql = "SELECT * FROM motorhomes";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public Motorhome addMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhomes VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        template.update(sql, m.getReg_number(), m.getMotor_model(), m.getStatus_rent(), m.getMileage(), m.getCapacity());
        return null;
    }

    public Motorhome findMotorhomeById(int id) {
        String sql ="SELECT * FROM motorhomes WHERE motor_id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome motorhome = template.queryForObject(sql, rowMapper, id);
        return motorhome;
    }

    public void deleteMotorhome(int id) {
        String sql = "DELETE FROM motorhomes WHERE motor_id = ?";
        template.update(sql, id);
    }

    public void updateMotorhome(int id, Motorhome m) {
        String sql = "UPDATE motorhomes SET reg_number = ?, motor_model = ?, status_rent = ?, mileage = ?, capacity = ? WHERE motor_id = ?";
        template.update(sql, m.getReg_number(), m.getMotor_model(), m.getStatus_rent(), m.getMileage(), m.getCapacity(), id);
    }
}
