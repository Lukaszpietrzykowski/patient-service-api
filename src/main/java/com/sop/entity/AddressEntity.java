package com.sop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
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

    public AddressEntity updateWith(AddressEntity address) {
        return new AddressEntity(
                this.id,
                address.city,
                address.street,
                address.streetNumber,
                address.postalCode,
                address.country,
                address.lat,
                address.lng);
    }
}


