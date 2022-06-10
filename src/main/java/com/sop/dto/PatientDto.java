package com.sop.dto;

import com.sop.entity.PatientEntity;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class PatientDto {
    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDateTime birthDate;
    private long age;
    private GenderEnum gender;
    private PriorityEnum priority;
    private LocalDateTime dischargeDate;
    private LocalDateTime registrationDate;
    private long hospitalId;
    private long departmentId;

    public static PatientDto of(PatientEntity patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .age(patient.getAge())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .priority(patient.getPriority())
                .dischargeDate(patient.getDischargeDate())
                .registrationDate(patient.getRegistrationDate())
                .hospitalId(patient.getDepartment().getHospital().getId())
                .departmentId(patient.getDepartment().getId())
                .build();
    }

    public static List<PatientDto> listOf(List<PatientEntity> patients) {
        return patients.stream()
                .map(PatientDto::of)
                .toList();
    }
}
