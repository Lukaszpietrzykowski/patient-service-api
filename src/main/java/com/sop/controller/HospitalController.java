package com.sop.controller;

import com.sop.creators.HospitalCreator;
import com.sop.entity.HospitalEntity;
import com.sop.dto.HospitalDto;
import com.sop.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private final HospitalService hospitalService;

    @GetMapping
    public List<HospitalDto> getHospitalList() {

        return hospitalService.getHospitals();
    }

    @GetMapping("/{id}")
    public HospitalDto getHospitals(@PathVariable long id){
        return HospitalService.getHospital(id);
    }

    @PostMapping("/add")
    public  HospitalDto addHospital(@RequestBody HospitalCreator hospitalEntity)
    {
        return hospitalService.createHospital(hospitalEntity);
    }
    @PutMapping("/update/{id}")
    public HospitalDto updateAddress(@PathVariable Long id, @RequestBody HospitalCreator hospitalCreator){
        return  hospitalService.updateHospital(id, hospitalCreator);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteHospital(@PathVariable Long id){
        hospitalService.deleteHospital(id);
    }

}
