package com.sop.controller;

import com.sop.creators.PatientCreator;
import com.sop.dto.PatientDto;
import com.sop.service.PatientService;
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
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getPatientList() {
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public PatientDto getPatienet(@PathVariable long id) {
        return patientService.getPatient(id);
    }

    @GetMapping("/admin/archived/all/")
    public List<PatientDto> getArchivedPatients() {
        return patientService.getArchivedPatients();
    }

    @PostMapping("/add")
    public void createPatient(@RequestBody PatientCreator patient) {
        patientService.createPatient(patient);
    }

    @PostMapping("/visit")
    public void createVisit(@RequestBody PatientCreator patient) {
        patientService.createVisit(patient);
    }

    @PutMapping("/update/{id}")
    public void updatePatient(@PathVariable long id, @RequestBody PatientCreator patient) {
        patientService.updatePatient(id, patient);
    }

    @PutMapping("/update/medical-history/{id}")
    public void updateMedicalHistory(@PathVariable long id, @RequestBody String medicalHistory) {
        patientService.updateMedicalHistory(id, medicalHistory);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
    }

}
