package com.sop.controller;

import com.sop.creators.HospitalCreator;
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

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/all")
    public List<HospitalDto> getHospitalList() {
        return hospitalService.getHospitals();
    }

    @GetMapping("/{id}")
    public HospitalDto getHospitals(@PathVariable long id) {
        return hospitalService.getHospital(id);
    }

    @PostMapping("/add")
    public HospitalDto addHospital(@RequestBody HospitalCreator hospital) {
        return hospitalService.createHospital(hospital);
    }

    @PutMapping("/update/{id}")
    public HospitalDto updateAddress(@PathVariable long id, @RequestBody HospitalCreator hospital) {
        return hospitalService.updateHospital(id, hospital);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHospital(@PathVariable long id) {
        hospitalService.deleteHospital(id);
    }

}
