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
        return modelRepo.fetchAll();
    }

    public void addModel(Model m){
        modelRepo.addModel(m);
    }

    public Model findModelById(int id) {
        return modelRepo.findModelById(id);
    }

    public void deleteModel(int id) {
        modelRepo.deleteModel(id);
    }

    public void updateModel(int id, Model m) {
        modelRepo.updateModel(id, m);
    }
}
