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

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String city;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String country;
    private double lat;
    private double lng;

    public static AddressEntity of(AddressCreator address) {
        return AddressEntity.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

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

