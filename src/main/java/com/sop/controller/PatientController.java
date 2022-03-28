package com.sop.controller;

import com.sop.dto.PatientDto;
import com.sop.dto.UserDto;
import com.sop.entity.PatientEntity;
import com.sop.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping("/all") // nazwy do ustalenia
    public List<PatientDto> getPatientList() {
        return patientService.getPatients();
    }

    @PostMapping("/add") // nazwy do ustalenia
    public boolean addPatient(@RequestBody PatientEntity patient) {
        patientService.addPatient(patient);
        return true;
    }

}
