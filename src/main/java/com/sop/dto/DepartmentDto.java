package com.sop.dto;

import com.sop.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DepartmentDto {

    private long id;
    private String name;
    private List<PatientDto> patents;
    private long availableBeds;

    public static DepartmentDto of(DepartmentEntity department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .patents(PatientDto.ofList(department.getPatients()))
                .availableBeds(department.getAvailableBeds())
                .build();
    }

    public static List<DepartmentDto> ofList(List<DepartmentEntity> departments) {
        return departments.stream()
                .map(DepartmentDto::of)
                .toList();
    }
}
