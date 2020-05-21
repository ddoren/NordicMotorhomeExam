package com.example.demo.Repository;

import com.example.demo.Model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelRepo {
    @Autowired
    JdbcTemplate template;

    public List<Model> fetchAll(){
        //sql Code
        return null;
    }

    public Model addModel(Model m){
        //sql code
        return null;
    }

    public Model findModelById(int id) {
        //sql code
        return null;
    }

    public Boolean deleteModel(int id) {
        //sql code
        return null;
    }

    public Model updateModel(int id, Model m) {
        //sql code
        return null;
    }
}
