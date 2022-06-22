package com.sop.controller;

import com.sop.creators.PatientCreator;
import com.sop.dto.PatientDto;
import com.sop.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Klasa UserController to klasa zawierająca funkcjonalności wykorzystywane
 * w tworzeniu REST API umożliwijąca wszelkie operacje związane z pacjentem.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    /**
     * Przechowuje obiekt typu PatientService.
     */
    private final PatientService patientService;

    /**
     * Zwraca listę wszystkich dostępnych pacjentów typu PatientDto.
     * @return zwraca listę wszystkich pacjentów.
     */
    @GetMapping
    public List<PatientDto> getPatientList() {
        return patientService.getPatients();
    }

    /**
     * Zwraca obiekt typu PatientDto na podstawie podanego adresu id.
     * @param id pacjenta.
     * @return zwraca znaleziony oddział typu PatientDto.
     */
    @GetMapping("/{id}")
    public PatientDto getPatienet(@PathVariable long id) {
        return patientService.getPatient(id);
    }

    /**
     * Tworzy pacjenta.
     * @param patient obiekt typu PatientCreator przechowujący pacjenta.
     */
    @PostMapping("/add")
    public void createPatient(@RequestBody PatientCreator patient) {
        patientService.createPatient(patient);
    }

    /**
     * Tworzy wizytę dla podanego pacjenta.
     * @param patient obiekt typu PatientCreator przechowujący pacjenta.
     */
    @PostMapping("/visit")
    public void createVisit(@RequestBody PatientCreator patient) {
        patientService.createVisit(patient);
    }

    /**
     * Edytuje pacjenta o podanym id.
     * @param id pacjenta.
     * @param patient obiekt typu PatientCreator przechowujący pacjenta.
     */
    @PutMapping("/update/{id}")
    public void updatePatient(@PathVariable long id, @RequestBody PatientCreator patient) {
        patientService.updatePatient(id,patient);
    }

    /**
     * Aktualizuje medyczną historię pacjenta o podanym adresie id.
     * @param id pacjenta.
     * @param medicalHistory obiekt typu String przechowujący historię medyczną pacjenta.
     */
    @PutMapping("/update/medical-history/{id}")
    public void updateMedicalHistory(@PathVariable long id, @RequestBody String medicalHistory) {
        patientService.updateMedicalHistory(id, medicalHistory);
    }

    /**
     * Usuwa pacjenta na podstawie podanego id.
     * @param id pacjenta.
     */
    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
    }

}
