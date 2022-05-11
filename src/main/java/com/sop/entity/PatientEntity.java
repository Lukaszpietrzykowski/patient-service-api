package com.sop.entity;

import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "PATIENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String pesel;
    private long age;
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    private LocalDateTime dischargeDate;
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    DepartmentEntity department;

    public PatientEntity updateWith(PatientEntity patient) {
        patient.setId(this.getId());
        return patient;
    }

    public static PatientEntity of(PatientCreator patient, LocalDateTime registrationDate) {
        return PatientEntity.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .age(Period.between(patient.getBirthDate().toLocalDate(), LocalDate.now()).getYears())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .priority(patient.getPriority())
                .dischargeDate(null)
                .registrationDate(registrationDate)
                .build();
    }

}
