package com.sop.dto;

import com.sop.entity.PatientEntity;
import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy {@link PatientEntity}, służąca do operacji na pacjencie.
 */
@AllArgsConstructor
@Builder
@Getter
public class PatientDto {
    /**
     * Zmienna przechowująca adres id pacjenta.
     */
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
     * Zmienna typu {@link LocalDateTime} przechowująca datę urodzenia pacjenta.
     */
    private LocalDateTime birthDate;
    /**
     * Zmienna typu Long przechowująca wiek użytkownika.
     */
    private long age;
    /**
     * Zmienna typu {@link GenderEnum} przechowująca płeć pacjenta.
     */
    private GenderEnum gender;
    /**
     * Zmienna typu {@link PriorityEnum} przechowująca priorytet pacjenta.
     */
    private PriorityEnum priority;
    /**
     * Zmienna typu {@link LocalDateTime} przechowująca datę wypisania pacjenta.
     */
    private LocalDateTime dischargeDate;
    /**
     * Zmienna typu {@link LocalDateTime} przechowująca datę rejestracji pacjenta.
     */
    private LocalDateTime registrationDate;
    /**
     * Zmienna typu long przechowująca id szpitala w którym przebywa użytkownik.
     */
    private long hospitalId;
    /**
     * Zmienna typu Long przechowująca id oddziału do którego przypisany jest pacjent.
     */
    private long departmentId;
    /**
     * Zmienna typu String przechowująca historię medyczną pacjenta.
     */
    private String medicalHistory;

    /**
     * Konwertuje obiekt klasy {@link PatientEntity} na obiekt klasy {@link PatientDto}.
     *
     * @param patient zmienna przechowująca pacjenta typu {@link PatientEntity} który chcemy przekonwertować.
     * @return zwraca przekonwertowanego pacjenta typu {@link PatientDto}.
     */
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
                .medicalHistory(patient.getMedicalHistory())
                .build();
    }

    /**
     * Konwertuje listę przechowującą obiekty typu {@link PatientEntity} na listę zawierającą obiekty typu {@link PatientDto}
     *
     * @param patients lista przechowująca pacjentów typu {@link PatientEntity}.
     * @return zwraca przekonwertowaną listę pacjentów typu {@link PatientDto}.
     */
    public static List<PatientDto> listOf(List<PatientEntity> patients) {
        return patients.stream()
                .map(PatientDto::of)
                .toList();
    }
}