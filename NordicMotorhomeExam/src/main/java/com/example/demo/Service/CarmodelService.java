package com.example.demo.Service;

import com.example.demo.Model.Carmodel;
import com.example.demo.Repository.CarmodelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarmodelService {
    @Autowired
    CarmodelRepo carmodelRepo;

    public List<Carmodel> fetchAll(){
        return carmodelRepo.fetchAll();
    }

    public void addModel(Carmodel m){
        carmodelRepo.addModel(m);
    }

    public Carmodel findModelById(int id) {
        return carmodelRepo.findModelById(id);
    }

    public void deleteModel(int id) {
        carmodelRepo.deleteModel(id);
    }

    public void updateModel(int id, Carmodel m) {
        carmodelRepo.updateModel(id, m);
    }
}
