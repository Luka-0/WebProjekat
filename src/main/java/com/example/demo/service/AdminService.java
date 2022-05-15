package com.example.demo.service;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Menadzer;
import  com.example.demo.entity.Dostavljac;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.DostavljacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private  MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    public Admin save(Admin admin){
        return  this.adminRepository.save(admin);
    }

    public Menadzer saveMenadzer(Menadzer noviMenadzer){
        return this.menadzerService.save(noviMenadzer);
    }

    public Dostavljac saveDostavljac(Dostavljac dostavljac){
        return this.dostavljacService.save(dostavljac);
    }
}
