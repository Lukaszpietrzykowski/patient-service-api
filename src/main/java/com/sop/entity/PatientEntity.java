package com.sop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PATIENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
