package com.sop.creators;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy {@link com.sop.entity.AddressEntity}, służąca do tworzenia adresu
 */
@AllArgsConstructor
@Getter
public class AddressCreator {
    /**
     * Zmienna typu String przechowująca nazwę miasta.
     */
    private String city;
    /**
     * Zmienna typu String przechowująca nazwę ulicy.
     */
    private String street;
    /**
     * Zmienna typu String przechowująca numer budynku.
     */
    private String streetNumber;
    /**
     * Zmienna typu String przechowująca kod pocztowy.
     */
    private String postalCode;
    /**
     * Zmienna typu String przechowująca nazwę kraju.
     */
    private String country;

    private double lat;
    private double lng;
}