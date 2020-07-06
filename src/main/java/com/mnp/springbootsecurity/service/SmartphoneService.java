package com.mnp.springbootsecurity.service;

import com.mnp.springbootsecurity.model.Smartphone;
import com.mnp.springbootsecurity.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmartphoneService {

    private SmartphoneRepository smartphoneRepository;

    @Autowired
    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    public List<Smartphone> getListSmartphones(){
        return smartphoneRepository.findAll();
    }

    public Optional<Smartphone> getSmartphone(int id){
        return smartphoneRepository.findById(id);
    }

    public Smartphone saveSmartphone(Smartphone smartphone){
        return smartphoneRepository.save(smartphone);
    }

    public void deleteSmartphone(int id){
        smartphoneRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dbInit(){
        smartphoneRepository.save(new Smartphone("Samsung Galaxy S20", 850));
        smartphoneRepository.save(new Smartphone("Apple iPhone 11 Pro", 950));
    }
}
