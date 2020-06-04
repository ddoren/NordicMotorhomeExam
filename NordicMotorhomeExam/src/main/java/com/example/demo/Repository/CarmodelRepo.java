package com.example.demo.Repository;

import com.example.demo.Model.Carmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarmodelRepo {

    @Autowired
    JdbcTemplate template;

    public List<Carmodel> fetchAll(){
        String sql = "SELECT * FROM carmodel";
        RowMapper<Carmodel> rowMapper = new BeanPropertyRowMapper<>(Carmodel.class);
        return template.query(sql, rowMapper);
    }

    public void addModel(Carmodel m){
        String sql = "INSERT INTO carmodel VALUES (DEFAULT , ?, ?, ?, ?, ?, ?);";
        template.update(sql,m.getModel_id(), m.getModel_name(), m.getModel_type(), m.getBrand(), m.getSize(), m.getLayout(), m.getSeats());
    }

    public Carmodel findModelById(int id) {
        String sql = "SELECT * FROM carmodel WHERE model_id = ?";
        RowMapper<Carmodel> rowMapper = new BeanPropertyRowMapper<>(Carmodel.class);
        Carmodel model = template.queryForObject(sql, rowMapper, id);
        return model;
    }

    public void deleteModel(int id) {
        String sql = "DELETE FROM carmodel WHERE model_id = ?;";
        template.update(sql, id);
    }

    public void updateModel(int id, Carmodel m) {
        String sql = "UPDATE carmodel SET model_name = ?, model_type = ?, brand = ?, size = ?, layout = ?, seats = ? WHERE model_id = ?";
        template.update(sql, m.getModel_name(), m.getModel_type(), m.getBrand(), m.getSize(), m.getLayout(), m.getSeats(), id);
    }
}
