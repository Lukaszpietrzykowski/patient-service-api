package com.sop.controller;

import com.sop.creators.HospitalCreator;
import com.sop.dto.DepartmentDto;
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

/**
 * Klasa HospitalController to klasa zawierająca funkcjonalności wykorzystywane
 * w tworzeniu REST API umożliwijąca wszelkie operacje związane z szpitalem.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    /**
     * Przechowuję obiekt typu {@link HospitalService}.
     */
    private final HospitalService hospitalService;

    /**
     * Zwraca listę wszystkich dostępnych szpitali typu {@link HospitalDto} gdy liczba dostępnych
     * łóżek jest wieksza od 0
     *
     * @return zwraca listę szpitali pod warunkiem, że szpitale posiadają dotsępne łóżka.
     */
    @GetMapping("/all")
    public List<HospitalDto> getHospitalList() {
        return hospitalService.getHospitals();
    }

    /**
     * Zwraca listę wszystkich dostępnych szpitali typu {@link HospitalDto} bez względu na to
     * czy szpital posiada dostępne łóżka.
     *
     * @return zwraca listę szpitali bez względu na stan dostępnych łóżek.
     */
    @GetMapping("/all/details")
    public List<HospitalDto> getHospitalListWithDetails() {
        return hospitalService.getHospitalsDetails();
    }

    /**
     * Zwraca obiekt typu {@link HospitalDto} na podstawie podanego adresu id.
     *
     * @param id szpitalu.
     * @return zwraca znaleziony szpital typu {@link HospitalDto}.
     */
    @GetMapping("/{id}")
    public HospitalDto getHospital(@PathVariable long id) {
        return hospitalService.getHospital(id);

    }

    /**
     * Zwraca obiekt typu {@link com.sop.dto.DepartmentDto.DepartmentDtoShort} na podtawie podanego adresu id szpitala.
     *
     * @param id szpitala
     * @return zwraca listę wszystkich oddziałów dla szpitala o podanym adresie id.
     */
    @GetMapping("/{id}/departments")
    public List<DepartmentDto.DepartmentDtoShort> getHospitalDepartments(@PathVariable long id) {
        return hospitalService.getHospitalDepartments(id);
    }

    /**
     * Tworzy szpital.
     *
     * @param hospital obiekt typu {@link HospitalCreator} przechowujący oddział szpitalu.
     * @return zwraca obiekt typu {@link HospitalDto}.
     */
    @PostMapping("/add")
    public HospitalDto addHospital(@RequestBody HospitalCreator hospital) {
        return hospitalService.createHospital(hospital);
    }

    /**
     * Edytuje szpital o podanym id.
     *
     * @param id       szpitala.
     * @param hospital obiekt typu {@link HospitalCreator} przechowujący oddział szpitalu.
     * @return zwraca obiekt typu {@link HospitalDto}.
     */
    @PutMapping("/update/{id}")
    public HospitalDto updateAddress(@PathVariable long id, @RequestBody HospitalCreator hospital) {
        return hospitalService.updateHospital(id, hospital);
    }

    /**
     * Usuwa podany szpital na podstawie podanego id.
     *
     * @param id szpitala.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteHospital(@PathVariable long id) {
        hospitalService.deleteHospital(id);
    }
}