package com.sop.dto;

import com.sop.entity.AddressEntity;
import com.sop.entity.HospitalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HospitalDto {
    private long id;
    private String name;
    private AddressEntity address;
    private List<DepartmentDto> departments;

    public static HospitalDto of(HospitalEntity hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .departments(DepartmentDto.ofList(hospital.getDepartments()))
                .build();
    }
}
