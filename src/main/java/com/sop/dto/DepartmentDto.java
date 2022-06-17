package com.sop.dto;

import com.sop.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
                .patents(PatientDto.listOf(department.getPatients()))
                .availableBeds(department.getAvailableBeds())
                .build();
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class DepartmentDtoShort {
        private long id;
        private String name;
        private long availableBeds;
        private long remainingBeds;

        public static DepartmentDtoShort of(DepartmentEntity department) {
            return DepartmentDtoShort.builder().
                    id(department.getId())
                    .name(department.getName())
                    .availableBeds(department.getAvailableBeds())
                    .remainingBeds(getRemainingBeds(department))
                    .build();
        }

        private static long getRemainingBeds(DepartmentEntity department) {
            return department.getAvailableBeds() - department.getPatients().size();
        }

        public static List<DepartmentDtoShort> listOf(List<DepartmentEntity> departments) {
            return departments.stream()
                    .map(DepartmentDtoShort::of)
                    .filter(department -> department.getRemainingBeds() > 0)
                    .toList();
        }

        public static List<DepartmentDtoShort> listOfWithDetails(List<DepartmentEntity> departments) {
            return departments.stream()
                    .map(DepartmentDtoShort::of)
                    .toList();
        }
    }
}
