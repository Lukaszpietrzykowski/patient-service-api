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
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy PatientEntity, służąca do operacji na pacjencie.
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
     * Zmienna typu LocalDateTime przechowująca datę urodzenia pacjenta.
     */
    private LocalDateTime birthDate;
    /**
     * Zmienna typu Long przechowująca wiek użytkownika.
     */
    private long age;
    /**
     * Zmienna typu GenderEnum przechowująca płeć pacjenta.
     */
    private GenderEnum gender;
    /**
     * Zmienna typu PriorityEnum przechowująca priorytet pacjenta.
     */
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
     * Konwertuje obiekt klasy PatientEntity na obiekt klasy PatientDto.
     * @param patient zmienna przechowująca pacjenta typu PatientEntity który chcemy przekonwertować.
     * @return zwraca przekonwertowanego pacjenta typu PatientDto.
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
     * Konwertuje listę przechowującą obiekty typu PatientEntity na listę zawierającą obiekty typu PatientDto
     * @param patients lista przechowująca pacjentów typu PatientEntity.
     * @return zwraca przekonwertowaną listę pacjentów typu PatientDto.
     */
    public static List<PatientDto> listOf(List<PatientEntity> patients) {
        return patients.stream()
                .map(PatientDto::of)
                .toList();
    }
}
