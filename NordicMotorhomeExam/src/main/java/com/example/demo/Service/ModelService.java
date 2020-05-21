package com.example.demo.Service;

import com.example.demo.Model.Model;
import com.example.demo.Repository.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    ModelRepo modelRepo;

    public List<Model> fetchAll(){
        modelRepo.fetchAll();
        return null;
    }

    public Model addModel(Model m){
        modelRepo.addModel(m);
        return null;
    }

    public Model findModelById(int id) {
        modelRepo.findModelById(id);
        return null;
    }

    public Boolean deleteModel(int id) {
        modelRepo.deleteModel(id);
        return null;
    }

    public Model updateModel(int id, Model m) {
        modelRepo.updateModel(id, m);
        return null;
    }
}
