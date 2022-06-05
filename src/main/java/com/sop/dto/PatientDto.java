package com.sop.dto;

import com.sop.entity.PatientEntity;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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
    private LocalDate birthDate;
    private long age;
    private GenderEnum gender;
    private PriorityEnum priority;
    private LocalDate dischargeDate;
    private LocalDate registrationDate;


    public static PatientDto of(PatientEntity patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .age(patient.getAge())
                .birthDate(patient.getBirthDate().toLocalDate())
                .gender(patient.getGender())
                .priority(patient.getPriority())
                .dischargeDate(patient.getDischargeDate().toLocalDate())
                .registrationDate(patient.getRegistrationDate().toLocalDate())
                .build();
    }

    public static List<PatientDto> listOf(List<PatientEntity> patients) {
        return patients.stream()
                .map(PatientDto::of)
                .toList();
    }
}
