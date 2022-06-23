package com.sop.creators;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy {@link com.sop.entity.HospitalEntity}, służaca do tworzenia szpitalu.
 */
@AllArgsConstructor
@Getter
public class HospitalCreator {
    /**
     * Zmienna typu String przechowująca nazwę szpitala.
     */
    private String name;
    /**
     * Zmienna typu AddressCreator przechowująca adres szpitala.
     */
    private AddressCreator address;
}