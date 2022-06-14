package com.sop.dto;

import com.sop.entity.HospitalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class HospitalDto {
    private long id;
    private String name;
    private AddressDto address;
    private List<DepartmentDto.DepartmentDtoShort> departments;

    public static HospitalDto of(HospitalEntity hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(AddressDto.of(hospital.getAddress()))
                .departments(DepartmentDto.DepartmentDtoShort.listOf(hospital.getDepartments()))
                .build();
    }

    public static HospitalDto ofWithDetails(HospitalEntity hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(AddressDto.of(hospital.getAddress()))
                .departments(DepartmentDto.DepartmentDtoShort.listOfWithDetails(hospital.getDepartments()))
                .build();
    }
}
