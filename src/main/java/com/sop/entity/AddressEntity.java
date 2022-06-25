package com.sop.entity;

import com.sop.creators.AddressCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Klasa reprezentująca adresy (obiekty/rekordy) znajdujące się w bazie danych.
 */
@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddressEntity {

    /**
     * Zmienna typu long przechowująca id adresu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    /**
     * Konwertuje obiekt klasy {@link AddressCreator} na obiekt klasy {@link AddressEntity}.
     *
     * @param address zmienna przechowujący adres typu {@link AddressCreator} który chcemy przekonwertować.
     * @return zwraca przekonwertowany adres typu {@link AddressEntity}.
     */
    public static AddressEntity of(AddressCreator address) {
        return AddressEntity.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .lat(address.getLat())
                .lng(address.getLng())
                .build();
    }

    /**
     * Edytuję adres.
     *
     * @param address zmienna przechowujący adres typu {@link AddressEntity}, przekazany do edycji.
     * @return zwraca zedytowany adres typu {@link AddressEntity}.
     */
    public AddressEntity updateWith(AddressEntity address) {
        return AddressEntity.builder()
                .id(this.id)
                .city(address.getCity())
                .street(address.getStreet())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .lat(address.getLat())
                .lng(address.getLng())
                .build();
    }
}