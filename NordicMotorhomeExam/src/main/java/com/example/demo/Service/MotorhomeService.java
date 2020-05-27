package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {
    @Autowired
    MotorhomeRepo motorhomeRepo;

    public List<Motorhome> fetchAll() {
        motorhomeRepo.fetchAll();
        return null;
    }

    public Motorhome addMotorhome(Motorhome m) {
        motorhomeRepo.addMotorhome(m);
        return null;
    }

    public Motorhome findMotorhomeById(int id) {
        motorhomeRepo.findMotorhomeById(id);
        return null;
    }

    public void deletePerson(int id) {
        motorhomeRepo.deletePerson(id);
    }

    public void updateMotorhome(int id, Motorhome m) {
        motorhomeRepo.updateMotorhome(id, m);
    }
}

