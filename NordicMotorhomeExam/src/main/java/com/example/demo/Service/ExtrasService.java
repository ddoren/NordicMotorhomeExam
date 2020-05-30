package com.example.demo.Service;


import com.example.demo.Model.Extras;
import com.example.demo.Repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtrasService {
    @Autowired
    ExtrasRepository extrasRepository;
    public List<Extras> fetchAll()
    {
        return extrasRepository.fetchAll();
    }
    public Extras addExtras(Extras ex)
    {
        return extrasRepository.addExtras(ex);
    }
    public Extras findExtraByID(int id)
    {
        return extrasRepository.findExtraByID(id);
    }
    public void deleteExtra(int id)
    {
        extrasRepository.deleteExtra(id);
    }
    public void updateExtra(Extras ex,int id)
    {
        extrasRepository.updateExtra(ex,id);
    }
}