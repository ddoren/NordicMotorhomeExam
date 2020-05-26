package com.example.demo.Repository;

import com.example.demo.Model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelRepo {
    @Autowired
    JdbcTemplate template;

    public List<Model> fetchAll(){
        String sql = "SELECT * FROM model;";
        RowMapper<Model> rowMapper = new BeanPropertyRowMapper<>(Model.class);
        return template.query(sql, rowMapper);
    }

    public void addModel(Model m){
        String sql = "INSERT INTO model VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
        template.update(sql, m.getModel_name(), m.getModel_type(), m.getBrand(), m.getSize(), m.getLayout(), m.getSeats());
    }

    public Model findModelById(int id) {
        String sql = "SELECT * FROM model WHERE model_id = ?";
        RowMapper<Model> rowMapper = new BeanPropertyRowMapper<>(Model.class);
        Model model = template.queryForObject(sql, rowMapper, id);
        return model;
    }

    public void deleteModel(int id) {
        String sql = "DELETE FROM model WHERE model_id = ?;";
        template.update(sql, id);
    }

    public void updateModel(int id, Model m) {
        String sql = "UPDATE model SET model_name = ?, model_type = ?, brand = ?, size = ?, layout = ?, seats = ? WHERE model_id = ?";
        template.update(sql, m.getModel_name(), m.getModel_type(), m.getBrand(), m.getSize(), m.getLayout(), m.getSize(), id);
    }
}
