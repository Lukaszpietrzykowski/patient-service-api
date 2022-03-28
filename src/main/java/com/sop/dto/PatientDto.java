package com.sop.dto;

import com.sop.entity.PatientEntity;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PatientDto {

    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDateTime birthDate;
    private GenderEnum gender;
    private PriorityEnum priority;
    private LocalDateTime dischargeDate;
    private LocalDateTime registrationDate;
    //private DepartmentDto department; (?) opcjonalne gdy ktoś stworzy DepartamentController

    public static PatientDto of(PatientEntity patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .priority(patient.getPriority())
                .dischargeDate(patient.getDischargeDate())
                .registrationDate(patient.getRegistrationDate())
                .build();
    } //opcjonalne gdy ktoś stworzy DepartamentController


}
