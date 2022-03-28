package com.sop.dto;

import com.sop.entity.DepartmentEntity;
import com.sop.entity.PatientEntity;
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
    private List<PatientEntity> patents;
    private long avalibleBeds;

    public static DepartmentDto of(DepartmentEntity department)
    {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .patents(department.getPatients())
                .avalibleBeds(department.getAvailableBeds())
                .build();
    }
}
