package com.sop.controller;

import com.sop.dto.HospitalDto;
import com.sop.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public List<HospitalDto> getHospitalList() {
        return hospitalService.getHospitals();
    }

}
