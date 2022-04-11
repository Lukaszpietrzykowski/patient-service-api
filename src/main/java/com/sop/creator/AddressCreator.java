package com.sop.creator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressCreator {
    private String city;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String country;
    private double lat;
    private double lng;
}
