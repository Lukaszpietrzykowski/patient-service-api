package com.sop.dto;

import com.sop.entity.HospitalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy HospitalEntity, służaca do operacji na szpitalu.
 */
@AllArgsConstructor
@Builder
@Getter
public class HospitalDto {
    /**
     * Zmienna typu long przechowująca id szpitala.
     */
    private long id;
    /**
     *  Zmienna typu String przechowująca nazwę szpitala.
     */
    private String name;
    /**
     * Zmienna typu AddressDto przechowująca adres szpitala.
     */
    private AddressDto address;
    /**
     *  Lista przechowująca oddziały typu DepartmentDtoShort.
     */
    private List<DepartmentDto.DepartmentDtoShort> departments;

    /**
     * Konwertuje obiekt klasy HospitalEntity na obiekt klasy HospitalDto.
     * @param hospital zmienna przechowująca szpital typu HospitalEntity który chcemy przekonwertować.
     * @return zwraca przekonwertowany szpital typu HospitalDto jeżeli w szpitlu istnieją wolne łóżka.
     */
    public static HospitalDto of(HospitalEntity hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(AddressDto.of(hospital.getAddress()))
                .departments(DepartmentDto.DepartmentDtoShort.listOf(hospital.getDepartments()))
                .build();
    }

    /**
     * Konwertuje obiekt klasy HospitalEntity na obiekt klasy HospitalDto.
     * @param hospital zmienna przechowujący szpital typu HospitalEntity który chcemy przekonwertować.
     * @return zwraca przekonwertowany szpital typu HospitalDto bez względu na ilość wolnych łóżek.
     */
    public static HospitalDto ofWithDetails(HospitalEntity hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(AddressDto.of(hospital.getAddress()))
                .departments(DepartmentDto.DepartmentDtoShort.listOfWithDetails(hospital.getDepartments()))
                .build();
    }
}
