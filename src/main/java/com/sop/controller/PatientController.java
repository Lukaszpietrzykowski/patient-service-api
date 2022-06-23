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

/**
 * Klasa PatientController to klasa zawierająca funkcjonalności wykorzystywane
 * w tworzeniu REST API umożliwijąca wszelkie operacje związane z pacjentem.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    /**
     * Przechowuje obiekt typu {@link PatientService}.
     */
    private final PatientService patientService;

    /**
     * Zwraca listę wszystkich dostępnych pacjentów typu {@link PatientDto}.
     *
     * @return zwraca listę wszystkich pacjentów.
     */
    @GetMapping
    public List<PatientDto> getPatientList() {
        return patientService.getPatients();
    }

    /**
     * Zwraca obiekt typu {@link PatientDto} na podstawie podanego adresu id.
     *
     * @param id pacjenta.
     * @return zwraca znaleziony oddział typu {@link PatientDto}.
     */
    @GetMapping("/{id}")
    public PatientDto getPatienet(@PathVariable long id) {
        return patientService.getPatient(id);
    }

    @GetMapping("/admin/archived/all/")
    public List<PatientDto> getArchivedPatients() {
        return patientService.getArchivedPatients();
    }

    /**
     * Tworzy pacjenta.
     *
     * @param patient obiekt typu {@link PatientCreator} przechowujący pacjenta.
     */
    @PostMapping("/add")
    public void createPatient(@RequestBody PatientCreator patient) {
        patientService.createPatient(patient);
    }

    /**
     * Tworzy wizytę dla podanego pacjenta.
     *
     * @param patient obiekt typu {@link PatientCreator} przechowujący pacjenta.
     */
    @PostMapping("/visit")
    public void createVisit(@RequestBody PatientCreator patient) {
        patientService.createVisit(patient);
    }

    /**
     * Edytuje pacjenta o podanym id.
     *
     * @param id      pacjenta.
     * @param patient obiekt typu {@link PatientCreator} przechowujący pacjenta.
     */
    @PutMapping("/update/{id}")
    public void updatePatient(@PathVariable long id, @RequestBody PatientCreator patient) {
        patientService.updatePatient(id, patient);
    }

    /**
     * Aktualizuje medyczną historię pacjenta o podanym adresie id.
     *
     * @param id             pacjenta.
     * @param medicalHistory obiekt typu String przechowujący historię medyczną pacjenta.
     */
    @PutMapping("/update/medical-history/{id}")
    public void updateMedicalHistory(@PathVariable long id, @RequestBody String medicalHistory) {
        patientService.updateMedicalHistory(id, medicalHistory);
    }

    /**
     * Usuwa pacjenta na podstawie podanego id.
     *
     * @param id pacjenta.
     */
    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
    }
}
