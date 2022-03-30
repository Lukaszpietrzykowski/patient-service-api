package com.sop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    private HospitalService hospitalService;

    @GetMapping
    public List<HospitalDto> getHospitalList() {
        return hospitalService.getHospitals();
    }

}
