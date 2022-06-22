package com.sop.entity;

import com.sop.creators.PatientCreator;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

/**
 * Klasa reprezentująca pacjentów (obiekty/rekordy) znajdujące się w bazie danych.
 */
@Entity
@Table(name = "PATIENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class PatientEntity {
    /**
     * Zmienna przechowująca adres id pacjenta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Zmienna typu String przechowująca imię pacjenta.
     */
    private String firstName;
    /**
     * Zmienna typu String przechowująca nazwę pacjenta.
     */
    private String lastName;
    /**
     * Zmienna typu String przechowująca pesel pacjenta.
     */
    private String pesel;
    /**
     * Zmienna typu Long przechowująca wiek użytkownika.
     */
    private long age;
    /**
     * Zmienna typu LocalDateTime przechowująca datę urodzenia pacjenta.
     */
    private LocalDateTime birthDate;

    /**
     * Zmienna typu GenderEnum przechowująca płeć pacjenta.
     */
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    /**
     * Zmienna typu PriorityEnum przechowująca priorytet pacjenta.
     */
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    /**
     * Zmienna typu LocalDateTime przechowująca datę wypisania pacjenta.
     */
    private LocalDateTime dischargeDate;
    /**
     * Zmienna typu LocalDateTime przechowująca datę rejestracji pacjenta.
     */
    private LocalDateTime registrationDate;

    /**
     * Zmienna typu DepartmentEntity przechowująca oddział w którym znajduję się pacjent.
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    DepartmentEntity department;

    /**
     * Zmienna typu String przechowująca historię medyczną pacjenta.
     */
    private String medicalHistory;

    /**
     * Edytuje pacjenta.
     * @param patient zmienna przechowujący pacjenta typu PatientEntity, przekazany do edycji.
     * @return zwraca zedytowanego pacjenta typu PatientEntity.
     */
    public PatientEntity updateWith(PatientEntity patient) {
        patient.setId(this.getId());
        return patient;
    }

    /**
     * Konwertuje obiekt klasy PatientCreator na obiekt klasy PatientEntity.
     * @param patient zmienna przechowująca pacjenta typu PatientCreator który chcemy przekonwertować.
     * @param registrationDate data rejestracji pacjenta.
     * @return zwraca przekonwertowanego pacjenta typu PatientEntity.
     */
    public static PatientEntity of(PatientCreator patient, LocalDateTime registrationDate) {
        return PatientEntity.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .age(patient.getAge())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .priority(patient.getPriority())
                .dischargeDate(null)
                .registrationDate(registrationDate)
                .build();
    }

}
