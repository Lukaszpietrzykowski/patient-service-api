package com.sop.dto;

import com.sop.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDto {
    private long id;
    private String city;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String country;

    public static AddressDto of(AddressEntity address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}


