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
        return motorhomeRepo.fetchAll();
    }

    public Motorhome addMotorhome(Motorhome m) {
        return motorhomeRepo.addMotorhome(m);
    }

    public Motorhome findMotorhomeById(int id) {
        return motorhomeRepo.findMotorhomeById(id);
    }

    public void deleteMotorhome(int id) {
        motorhomeRepo.deleteMotorhome(id);
    }

    public void updateMotorhome(int id, Motorhome m) {
        motorhomeRepo.updateMotorhome(id, m);
    }
}

