package com.sop.creators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressCreator {
    private String city;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String country;
    private double lat;
    private double lng;
}
