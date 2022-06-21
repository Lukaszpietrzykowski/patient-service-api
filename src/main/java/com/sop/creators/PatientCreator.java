package com.sop.creators;

import com.sop.enums.GenderEnum;
import com.sop.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy PatientEntity, służąca do tworzenia pacjenta.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PatientCreator {

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
     * Zmienna typu GenderEnum przechowująca płeć pacjenta.
     */
    private GenderEnum gender;
    /**
     * Zmienna typu PriorityEnum przechowująca priorytet dla pacjenta.
     */
    private PriorityEnum priority;
    /**
     * Zmienna typu LocalDateTime przechowująca datę rejestracji pacjenta.
     */
    private LocalDateTime registrationDate;
    /**
     * Zmienna typu Long przechowująca wiek użytkownika.
     */
    private Long age;
    /**
     * Zmienna typu Long przechowująca id oddziału do którego przypisany jest pacjent.
     */
    private Long departmentId;

}
