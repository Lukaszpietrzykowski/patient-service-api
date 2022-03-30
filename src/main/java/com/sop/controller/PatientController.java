package com.sop.controller;

import com.sop.dto.PatientDto;
import com.sop.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getPatientList() {
        return patientService.getPatients();
    }

}
