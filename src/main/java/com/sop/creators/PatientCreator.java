package com.sop.creators;

import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PatientCreator {

    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
    private GenderEnum gender;
    private PriorityEnum priority;
    private LocalDate registrationDate;
    private Long age;
    private Long departmentId;

}
