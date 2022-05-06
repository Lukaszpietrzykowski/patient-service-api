package com.sop.creators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalCreator {
    private String name;
    private AddressCreator address;
}
